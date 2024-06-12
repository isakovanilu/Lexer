import java.text.ParseException;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int currentPos;
    private Token currentToken;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
        currentPos = 0;
        currentToken = tokens.get(currentPos);
    }

    public ASTNode parse() {
        return expression();
        
    }

    private ASTNode expression() {
        ASTNode node = term();

        while(currentToken != null && (currentToken.type == Token.Type.PLUS || currentToken.type == Token.Type.MINUS)) {
            Token token = currentToken;
            consume(currentToken.type);
            node = new BinaryOpNode(node, term(), token);
        }

        return node;
    }

    private ASTNode term() {
        ASTNode node = factor();

        while(currentToken != null && (currentToken.type == Token.Type.MULTIPLE || currentToken.type == Token.Type.DIVIDE)) {
            Token token = currentToken;
            consume(currentToken.type);
            node = new BinaryOpNode(node, factor(), token);
        }

        return node;
        
    }

    private void consume(Token.Type type) {
        if (currentToken != null && currentToken.type == type){
            currentPos ++;
            if (currentPos < tokens.size()) {
                currentToken = tokens.get(currentPos);
            }else {
                currentToken = null;
            }

        } else {
            throw new ParserException("Unexpected Token: " + currentToken + " expected: " + type );
        }
    }

    private ASTNode factor() {
        Token token = currentToken;
        if (token.type == Token.Type.NUMBER) {
            consume(Token.Type.NUMBER);
            return new NumberNode(token);
        }
        if (token.type == Token.Type.LPAREN) {
            consume(Token.Type.LPAREN);
            ASTNode node = expression();
            consume(Token.Type.RPAREN);
            return node;

        }
        throw new ParserException("Unexpected token found for the factor: " + token);
    }

}