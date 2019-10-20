package AntiUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LRUstack {
    private List<Integer> list;

    public LRUstack(int size) {
        list = new ArrayList<>(Collections.nCopies(size, -1));
    }

    public void pushBack(int element) {
        if (hasContain(element)) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i) == element)
                    list.remove(i);
            }
            list.add(-1);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == -1) {
                    set(i, element);
                    break;
                }
            }
        } else if (isFull()) {
            popFront();
            list.add(element);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == -1) {
                    set(i, element);
                    break;
                }
            }
        }


    }

    public int get(int index) {
        return list.get(index);
    }

    private void set(int index, int element) {
        list.set(index, element);
    }

    private void popFront() {
        list.remove(0);
    }

    private boolean hasContain(int element) {
        for (int i : list) {
            if (element == i)
                return true;
        }
        return false;
    }

    private boolean isFull() {
        for (int i : list) {
            if (i == -1)
                return false;
        }
        return true;
    }

    public int getSize(){
        return list.size();
    }


}
