package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
// MemberRepository에 커서 두고 Ctrl + Shift + T => Create New Test
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤패턴
    private static final MemberRepository instance = new MemberRepository();

    // 싱글톤패턴인 경우 private로 생성자를 막아줘야한다. (아무나 생성하지 못하도록)
    private MemberRepository() {
    }

    // 무조건 getInstance만 접근이 가능하도록
    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    /**
     * @return
     * store에 담긴 모든 값을 꺼내서 새로운 ArrayList에 담아서 넘겨준다.
     * (new ArrayList에 값을 넣거나 밖에서 조작해도 스토어에 있는 value를 건드리지 않기 때문)
     */
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
