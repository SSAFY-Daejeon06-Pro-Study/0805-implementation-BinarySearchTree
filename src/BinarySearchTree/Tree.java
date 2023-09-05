
/**
 *
 * 자바 Tree Interface입니다. <br>
 * Tree는 BinarySearchTree에 의해 구현됩니다.
 *
 * @author kdgyun
 *
 * @version 1.0.003
 * @since 1.0.003
 *
 */

public interface Tree {
    /**
     * 지정된 요소가 트리에 없는 경우 요소를 추가합니다.
     *
     * @param value 트리에 추가할 요소
     * @return {@code true} 만약 트리에 지정 요소가 포함되지 않아 정상적으로 추가되었을 경우,
     *         else, {@code false}
     */
    boolean add(int value);

    /**
     * 지정된 요소가 트리에 있는 경우 해당 요소를 삭제합니다.
     *
     * @param value 트리에서 삭제할 특정 요소
     * @return 만약 트리에 지정 요소가 포함되어 정상적으로 삭제되었을 경우 해당 요소를 반환
     */
    Node remove(int value);

    /**
     * 현재 트리에 특정 요소가 포함되어있는지 여부를 반환합니다.
     *
     * @param value 트리에서 찾을 특정 요소
     * @return {@code true} 트리에 지정 요소가 포함되어 있을 경우,
     *         else, {@code false}
     */
    boolean contains(int value);
    /**
     * 현재 트리가 빈 상태(요소가 없는 상태)인지 여부를 반환합니다.
     *
     * @return {@code true} 트리가 비어있는 경우,
     *         else, {@code false}
     */
    boolean isEmpty();

    /**
     * 현재 트리의 요소의 개수를 반환합니다.
     *
     * @return 트리에 들어있는 요소의 개수를 반환
     */
    int size();

    /**
     * 트리의 모든 요소를 제거합니다.
     * 이 작업을 수행하면 비어있는 트리가 됩니다.
     */
    void clear();

    /**
     * 현재 트리의 모습을 출력합니다.
     */
    void print();
}
