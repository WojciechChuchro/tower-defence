class Node {
  constructor(value) {
    this.value = value;
    this.next = null;
  }
}

class LinkedList {
  constructor(value) {
    this.head = {
      value: value,
      next: null,
    };
    this.tail = this.head;
    this.length = 1;
  }

  append(value) {
    const newNode = new Node(value);

    this.tail.next = newNode;
    this.tail = newNode;
    this.length++;
  }

  prepend(value) {
    const newNode = new Node(value);

    newNode.next = this.head;
    this.head = newNode;
    this.length++;
  }

  insert(value, index) {
    let currentNode = this.head;
    let i = 0;
    while (currentNode !== null) {
      if (i === index) {
        currentNode.value = value;
        break;
      }
      i++;
      currentNode = currentNode.next;
    }
  }

  printList() {
    let currentNode = this.head;
    let str = "HEAD -> ";
    while (currentNode !== null) {
      str += `${currentNode.value} -> `;
      currentNode = currentNode.next;
    }
    str += "NULL";
    console.log(str);
  }
}

let myLinkedList = new LinkedList(10);

myLinkedList.append(5);
myLinkedList.append(16);
myLinkedList.prepend(1);
myLinkedList.insert(15, 2);
myLinkedList.printList();
