@Test
void testHandle_successfulFlow() {
    // 1. Mock config
    Map<String, String> npisMap = Map.of("SomeName", "ExpectedResult");
    when(failureConfig.getNpis()).thenReturn(npisMap);

    // 2. Mock job
    Map<String, Object> vars = new HashMap<>();
    vars.put("eventNote", Map.of("code", "0"));
    when(job.getVariablesAsMap()).thenReturn(vars);
    when(job.getKey()).thenReturn(123L);

    // 3. Mock Zeebe chain
    CompleteJobCommandStep1 command = mock(CompleteJobCommandStep1.class);
    when(jobClient.newCompleteCommand(anyLong())).thenReturn(command);
    when(command.variables(anyMap())).thenReturn(command);

    @SuppressWarnings("unchecked")
    ZeebeFuture<CompleteJobResponse> future = mock(ZeebeFuture.class);
    when(command.send()).thenReturn(future);
    when(future.join()).thenReturn(mock(CompleteJobResponse.class));

    // 4. Run worker
    worker.handle(jobClient, job);

    // 5. Capture argument passed into variables()
    ArgumentCaptor<Map<String, Object>> captor = ArgumentCaptor.forClass(Map.class);
    verify(command).variables(captor.capture());

    Map<String, Object> capturedVars = captor.getValue();

    // 6. Check hydratedResponse
    Object actualTransformed = capturedVars.get("hydratedResponse");
    Object expectedTransformed = "ok"; // <- yeh tum decide karoge tumhari logic ke hisaab se

    assertEquals(expectedTransformed, actualTransformed);
}
