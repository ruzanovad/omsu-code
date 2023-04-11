public enum Mark {
    A, // 5
    B, // 4
    C, // 3
    D, // 2
    PASSED,
    NOT_PASSED;
    public int toInt(){
        return switch (this) {
            case A -> 5;
            case B -> 4;
            case C -> 3;
            case D -> 2;
            case PASSED -> 1;
            case NOT_PASSED -> 0;
        };
    }
    @Override
    public String toString(){
        return switch (this){
            case A -> "отлично";
            case B -> "хорошо";
            case C -> "удовлетворительно";
            case D -> "неудовлетворительно";
            case PASSED -> "зачтено";
            case NOT_PASSED -> "не зачтено";
        };
    }

}
