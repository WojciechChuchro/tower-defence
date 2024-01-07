package Java.LeetCode;

/**
 * Given two non-empty linked lists representing two non-negative integers, this function adds the two numbers and returns the sum as a linked list.
 * The digits are stored in reverse order, and each node contains a single digit. The function assumes that the two numbers do not contain any leading zero, except the number 0 itself.
 * <br><br>
 *
 * <b>Example 1:</b>
 * <br>Input: l1 = [2,4,3], l2 = [5,6,4]
 * <br>Output: [7,0,8]
 * <br>Explanation: 342 + 465 = 807.
 *
 * <br><br>
 * <b>Example 2:</b>
 * <br>Input: l1 = [0], l2 = [0]
 * <br>Output: [0]
 *
 * <br><br>
 * <b>Example 3:</b>
 * <br>Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * <br>Output: [8,9,9,9,0,0,0,1]
 * <br>Explanation: 9999999 + 9999 = 10009998.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public int getNumber() {
        ListNode currentNode = this;
        StringBuilder num = new StringBuilder();
        while (currentNode != null) {
            num.append(currentNode.val);
            currentNode = currentNode.next;
        }

        num.reverse();
        return Integer.parseInt(String.valueOf(num));
    }

    public void append(int value) {
        ListNode newNode = new ListNode(value);
        ListNode current = this;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }
}

class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.append(4);
        l1.append(3);

        ListNode l2 = new ListNode(5);
        l2.append(6);
        l2.append(4);

        System.out.println(addTwoNumbers(l1, l2).getNumber());
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num1 = l1.getNumber();
        int num2 = l2.getNumber();

        String sum = String.valueOf(num1 + num2);

        ListNode result = new ListNode(Character.getNumericValue(sum.charAt(sum.length()-1)));

        for (int i = sum.length() - 2; i >= 0; i--) {
            result.append(Character.getNumericValue(sum.charAt(i)));
        }

        return result;
    }
}