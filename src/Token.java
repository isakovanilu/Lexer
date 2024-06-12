import java.util.List;
import java.util.Arrays;

public class Token {
    public enum Type {
        NUMBER, MULTIPLE, DIVIDE, PLUS, MINUS, LPAREN, RPAREN,
    }

    public final Type type;
    public final String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    // Optionally, override toString for easier debugging
    @Override
    public String toString() {
        return "Token{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                '}';
    }
}