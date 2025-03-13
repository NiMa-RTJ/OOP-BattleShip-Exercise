public class IsValidInput {

    boolean valid(String target) {
        if (target.length()==2) {
            char index0 = target.charAt(0);
            char index1 = target.charAt(1);
            if (index1 >= 'A' && index1 <= 'Z') return false;
            else {
                int index2 = Integer.parseInt(target.substring(1));
                return (index0 >= 'A' && index0 <= 'Z') && (index2 >= 0 && index2 <= 9);
            }

        }
        return false;
    }
}
