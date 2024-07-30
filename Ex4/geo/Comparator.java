package  geo;
import ex4.Ex4_Const;
import gui.GUI_Shape;

public class Comparator implements java.util.Comparator<GUI_Shape> {
    private int flag;

    public Comparator(int f) {
        this.flag = f;
    }

    public int compare(GUI_Shape o1, GUI_Shape o2) {
        int ans = 0;
        if (this.flag == Ex4_Const.Sort_By_toString) {
            ans = o1.toString().compareTo(o2.toString());
        } else if (this.flag == Ex4_Const.Sort_By_Anti_toString) {
            ans = o2.toString().compareTo(o1.toString());
        }

        if (this.flag == Ex4_Const.Sort_By_Area) {
            ans = Double.compare(o1.getShape().area(), o2.getShape().area());
        } else if (this.flag == Ex4_Const.Sort_By_Anti_Area) {
            ans = Double.compare(o2.getShape().area(), o1.getShape().area());
        }

        if (this.flag == Ex4_Const.Sort_By_Perimeter) {
            ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter());
        } else if (this.flag == Ex4_Const.Sort_By_Anti_Perimeter) {
            ans = Double.compare(o2.getShape().perimeter(), o1.getShape().perimeter());
        }

        if (this.flag == Ex4_Const.Sort_By_Tag) {
            ans = Integer.compare(o1.getTag(), o2.getTag());
        } else if (this.flag == Ex4_Const.Sort_By_Anti_Tag) {
            ans = Integer.compare(o2.getTag(), o1.getTag());
        }

        return ans;
    }
}