import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Interpreter {
    private final Map<String, Integer> valueTable = new HashMap<>();

    int visit(ASTNode node) {
        if (node instanceof BinaryOpNode binaryOpNode) {
            int left = visit(binaryOpNode.left);
            int right = visit(binaryOpNode.right);
            switch (binaryOpNode.operationToken.type) {
                case MINUS -> {
                    return left - right;
                }
                case PLUS -> {
                    return left + right;
                }
                case DIVIDE -> {
                    return left / right;
                }
                case MULTIPLY -> {
                    return left * right;
                }
                default -> throw new ParserException("Unexpected token: " + binaryOpNode.operationToken);
            }

        } else if (node instanceof NumberNode numberNode) {
            return numberNode.value;

        } else if (node instanceof VarDecl varDecl) {
            int rightExpressionResult = visit(varDecl.expr);
            valueTable.put(varDecl.varNode.name, rightExpressionResult);
            return rightExpressionResult;

        } else if (node instanceof Var var) {
            String varName = var.name;
            if (!valueTable.containsKey(varName)) {
                throw new ParserException("Variable not found: " + varName);
            }
            return valueTable.get(varName);

        } else if (node instanceof Assign assignNode) {
            int rightExpressionResult = visit(assignNode.right);
            valueTable.put(assignNode.left.name, rightExpressionResult);

        } else if (node instanceof Block block) {
            int result = 0;
            for (ASTNode statement : block.statements) {
                result = visit(statement);
            }
            return result;

        } else {
            throw new ParserException("Unexpected ASTNode: " + node.getClass().getCanonicalName());
        }
        return 0;
    }

}
