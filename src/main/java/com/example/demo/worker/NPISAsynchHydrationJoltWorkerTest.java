package au.com.optus.renaissanceCamunda.worker;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.command.CompleteJobCommandStep1;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.response.CompleteJobResponse;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NPISAsynchHydrationJoltWorkerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private JobClient jobClient;

    @Mock
    private ActivatedJob job;

    @InjectMocks
    private NPISAsynchHydrationJoltWorker worker;

    @Test
    void testHandle_withRealJsonSpecAndExpected() throws IOException {
        // 1. Load request variables (input JSON)
        Map<String, Object> jobVars = mapper.readValue(
                getClass().getResourceAsStream("/npis/npis_failure_sample_request.json"),
                new TypeReference<Map<String, Object>>() {}
        );

        // 2. Load JOLT spec
        List<Object> joltSpec = mapper.readValue(
                getClass().getResourceAsStream("/npis/jolt-spec-npis.json"),
                new TypeReference<List<Object>>() {}
        );

        // 2. Convert nested objects into stringified JSON (for worker compatibility)
        jobVars = JsonStringifyHelper.stringifyNested(jobVars);

        // 3. Add joltSpec into jobVars map (since worker reads it from variables.get("joltSpecNpis"))
        jobVars.put("joltSpecNpis", joltSpec);

        // 4. Load expected response
        Map<String, Object> expectedResponse = mapper.readValue(
                getClass().getResourceAsStream("/npis/npis_failure_sample_expected.json"),
                new TypeReference<Map<String, Object>>() {}
        );

        // 5. Mock job.getVariablesAsMap() only
        when(job.getVariablesAsMap()).thenReturn(jobVars);
        when(job.getKey()).thenReturn(123L);

        // 6. Mock Zeebe client chain
        CompleteJobCommandStep1 command = mock(CompleteJobCommandStep1.class);
        when(jobClient.newCompleteCommand(anyLong())).thenReturn(command);
        when(command.variables(anyMap())).thenReturn(command);

        @SuppressWarnings("unchecked")
        ZeebeFuture<CompleteJobResponse> future = mock(ZeebeFuture.class);
        when(command.send()).thenReturn(future);

        // 7. Run worker
        worker.handle(jobClient, job);

        // 8. Verify actual vs expected
        verify(command).variables(argThat(actualVars -> {
            assertEquals(expectedResponse, actualVars, "HydratedResponse did not match expected");
            return true;
        }));
    }
}
