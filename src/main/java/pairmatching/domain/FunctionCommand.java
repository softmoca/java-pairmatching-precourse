package pairmatching.domain;

public enum FunctionCommand {
    MACTING("1"),
    QUERY("2"),
    CLEAR("3"),
    QUIT("Q");

    private final String function;

    FunctionCommand(String function) {
        this.function = function;
    }

    public static FunctionCommand from(String function) {
        for (FunctionCommand functionCommand : values()) {
            if (functionCommand.function.equals(function)) {
                return functionCommand;
            }
        }
        throw new IllegalArgumentException("[ERROR] 기능 입력 에러");
    }
}
