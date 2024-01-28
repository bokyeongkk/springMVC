package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 최근 JUnit5에는 public이 없어도 동작한다.
class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    // AfterEach : 테스트가 끝날 때 마다 실행된다.
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);
        // when
        Item savedItem = itemRepository.save(item);
        // then
        Item findItem = itemRepository.findById(item.getId());
        // 검증
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        // when
        List<Item> result = itemRepository.findAll();
        // then
        // 검증
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();
        // when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);
        // then
        Item findItem = itemRepository.findById(itemId);
        // 검증
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
