import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SemanticAnalyzer {

    private final Stack<Set<String>> scopes = new Stack <>();

    void visit(ASTNode node) {
        if (node instanceof  BinaryOpNode) {
            visit(((BinaryOpNode)node).left);
            visit(((BinaryOpNode)node).right);

        } else  if (node instanceof  NumberNode) {
            // nothing

        } else  if (node instanceof  VarDecl varDecl) {
            String varName = varDecl.varNode.name;
            if (isVariableDefined(varName)) {
                throw new ParserException("Identifier already defined: " + varName);
            }
            scopes.peek().add(varName);
        } else  if (node instanceof  Var) {
            String varName = ((Var)node).name;
            if (!isVariableDefined(varName)) {
                throw new ParserException("Unexpected identifier: " + varName);
            }
        } else  if (node instanceof  Assign assignNode) {
            String varName = assignNode.left.name;
            if (!isVariableDefined(varName)) {
                throw new ParserException("Unexpected identifier: " + varName);
            }
            visit(assignNode.right);
        } else  if (node instanceof  Block block) {
            scopes.push(new HashSet<>()); // enter new scope
            for (ASTNode statement: block.statements) {
                visit(statement);
            }
            scopes.pop(); // exit scope

        }else {
            throw new ParserException("unexpected AST Node: " + node.getClass().getCanonicalName());
        }
    }

    private boolean isVariableDefined(String varName) {
        for (Set<String> scope: scopes) {
            if (scope.contains(varName)) return true;
        }
        return false;
    }
}
