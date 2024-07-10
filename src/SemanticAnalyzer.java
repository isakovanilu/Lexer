import java.util.Set;
import java.util.Stack;

public class SemanticAnalyzer {

    private final Stack<Set<String>> scopes = new Stack <>();

    void visit(ASTNode node) {
        if (node instanceof  BinaryOpNode) {

        } else  if (node instanceof  NumberNode) {
        } else  if (node instanceof  VarDecl) {
        } else  if (node instanceof  Var) {
        } else  if (node instanceof  Assign) {
        } else  if (node instanceof  Block) {

        }else {
            throw new ParserException("unexpected AST Node: " + node.getClass().getCanonicalName());
        }

    }
}
