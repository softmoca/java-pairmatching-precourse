package pairmatching.domain;

public enum RematchCommand {
    YES("네"),
    NO("아니오");

    private final String name;

    RematchCommand(String name) {
        this.name = name;
    }

    public static RematchCommand from(String name) {
        for (RematchCommand rematchCommand : values()) {
            if (rematchCommand.name.equals(name)) {
                return rematchCommand;
            }
        }
        throw new IllegalArgumentException("[ERROR] 재시도 입력 에러");


    }

}
