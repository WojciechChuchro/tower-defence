from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev = None
        curr = head
        while curr:
            save = curr.next
            curr.next = prev
            prev = curr
            curr = save
        return prev

    def reverseListRecursive(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head:
            return None

        new_head = head
        if head.next:
            new_head = self.reverseListRecursive(head.next)
            head.next.next = head
        head.next = None
        return new_head



s = Solution()

ln = ListNode(1)
ln.next = ListNode(2)
ln.next.next = ListNode(3)
print(s.reverseListRecursive(ln))
