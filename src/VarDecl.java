public class VarDecl extends ASTNode {
    public Var varNode;
    public ASTNode expr;

    public VarDecl(Var varNode, ASTNode expr) {
        this.varNode = varNode;
        this.expr = expr;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "VarDecl{" + varNode.name + '}');
        expr.print(indent + indent);
    }
}
