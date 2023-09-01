# 0805-implementation-BinarySearchTree
## 이진 검색 트리 구현

---

## 구현해야 하는 메서드

- add(int value)
    - value의 값을 트리에 추가
    - 이미 값이 존재한다면 추가하지 않음 (중복을 허용하지 않음)
    - 반환값 없음
- remove(int value)
    - value의 값을 트리에서 찾아 삭제
    - 값이 존재하지 않는다면 예외 발생 혹은 null 반환
    - 값이 존재한다면 해당 값 반환
- size()
    - 트리의 요소 개수 반환
- isEmpty()
    - 트리가 비었는지 여부를 반환
- contains(int value)
    - 트리가 value를 요소로 가지고 있는지 여부를 반환
- clear()
    - 트리 요소를 전부 삭제
    - 반환값 없음
- print()
    - 트리의 모습을 출력