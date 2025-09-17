package au.com.optus.renaissanceCamunda.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NPISAsynchHydrationJoltWorkerTest {

    @Mock
    private ServiceDiagnosticsNpisFailureConfig failureConfig;

    @Mock
    private JobClient jobClient;

    @Mock
    private ActivatedJob job;

    @InjectMocks
    private NPISAsynchHydrationJoltWorker worker;

    @Test
    void testHandle_successfulFlow() {
        // given
        Map<String, String> npisMap = Map.of("SomeName", "ExpectedResult");
        when(failureConfig.getNpis()).thenReturn(npisMap);

        Map<String, Object> vars = new HashMap<>();
        vars.put("eventNote", Map.of("code", "0"));
        when(job.getVariablesAsMap()).thenReturn(vars);
        when(job.getKey()).thenReturn(123L);

        // mock command chain
        JobClient.FinalCommandStep<?> command = mock(JobClient.FinalCommandStep.class);
        when(jobClient.newCompleteCommand(anyLong())).thenReturn(command);
        when(command.variables(anyMap())).thenReturn(command);

        // when
        worker.handle(jobClient, job);

        // then
        verify(failureConfig, atLeastOnce()).getNpis();
        verify(jobClient).newCompleteCommand(123L);
    }

    @Test
    void testHandle_invalidInput_shouldThrow() {
        // given invalid input
        when(job.getVariablesAsMap()).thenReturn(Map.of("eventNote", 123));

        // then expect exception
        assertThrows(IllegalArgumentException.class, () -> worker.handle(jobClient, job));
    }
}
