package magna.carta;

public interface Function<IN, OUT> {
    OUT apply(IN input);
}
