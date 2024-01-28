package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // item의 id가 Long 타입이므로 Long 타입으로 생성
    private static final Map<Long, Item> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    /**
     * item 저장
     * @param item
     * @return
     */
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    /**
     * item 조회
     * @param id
     * @return
     */
    public Item findById(Long id) {
        return store.get(id);
    }

    /**
     * item 전체 조회
     * @return
     */
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * item 수정
     * @param itemId
     * @param updateParam
     */
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);

        /*
            [참고]
            실무에서는 updateParam으로 id를 사용하지 않는데 get, set 처리가 되어 개발자가 혼란스러울 수 있다.
            -> name, price, quantity 세 개의 파라미터만 넣어둔 ItemDto와 같은 객체를 새로 생성하는 것이 좋다.
         */
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    /**
     * item 삭제
     */
    public void clearStore() {
        store.clear();
    }

}
