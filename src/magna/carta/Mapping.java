package magna.carta;

public interface Mapping<IN, OUT> {
    OUT apply(IN input);
}
