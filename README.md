# Redis_Prectice
07/20
NoSQL이란 비관계형 데이터베이스 KEY-VALUE나 컬럼, 문서형싱의 데이터 모델을 이용
(많은 양의 데이터를 효율적으로 처리, 데이터의 분산처리, 빠른 쓰기 및 데이터의 안정성이 필요할 때 사용)

레디스란 메모리 기반의 KEY-VALUE 구조 데이터 관리 시스템
모든 데이터를 메모리에 저장하고 조회하기 빠른 READ/WRITE 속도를 보장하는 비 관계형 데이터베이스

레디스는 크게 <String,Set,Sorted Set,Hash,List>의 데이터 형식을 지원

## 레디스 장점
1. 리스트,배열과 같은 데이터를 처리하는데 유용하다.
	VALUE 값으로 문자열, 리스트, Set, Sorted set, Hash 등 여러 데이터 형식을 지원하기에 다양한 방식으로 데이터를 활용 가능

2. 리스트형 데이터 입력과 삭제가 MySQL에 비해서 빠르다.
	여러 프로세스에서 동시에 같은 key에 대한 갱신을 요청할 경우, Atomic 처리로 데이터 부정합 방지Atomic 처리 함수를 제공

3. 메모리를 활용하면서 영속적인 데이터 보존
	명령어로 명시적으로 삭제, expires를 설정하지 않으면 데이터가 삭제되지 않음

4. 레디스 서버는 1개의 싱글 스레드로 수행되며, 따라서 서버 하나에 여러개의 서버를 띄우는 것이 가능하다.
	Master-Slave 형식으로 구성이 가능함, 데이터 분실 위험을 없애줌


## CRUD 명령어

keys : 현재의 키값 들을 확인하는 명령어
ex) 127.0.0.1 : 6379> keys *

set : 키/값을 저장하는 명령어
ex) 127.0.0.1 : 6379> set key value
     127.0.0.1 : 6379> keys *
     1) "key"	

mset : 여러개의 Key와 value를 한번에 입력

setex : Key와 value, expires(sec) 설정을 입력
(입력된 시간 이후에 소멸된다)

get : 키에 해당하는 값을 가져오는 명령어
ex) 127.0.0.1 : 6379> get key
     "value"

mget : 여러개의 Key값을 입력하는 Value를 동시에 리턴받는다.

del : 키와 해당하는 값을 삭제하는 명령어. 여러개의 키값을 지우는 dels는 없다.
ex) 127.0.0.1 : 6379> del key
     (integer)1
     127.0.0.1 : 6379> keys *
     (empty list or set)

lrange key start stop : list형태의 저장된 value값을 확인
ex 127.0.0.1 : 63796> lrange list 0 1
    1) "2"
    2) "1"



## 기타 명령어

flushall : 현재 저장되어 있는 모든 key를 삭제



## Data 삽입

List는 Push를 통하여 데이터를 저장
Hash는 Map 자료형과 사용이 거의 비슷, key-field-value를 작성하여 데이터를 삽입
Set은 같은 Key에 여러 value를 작성 할 수 있음
ZSet은 value에 Score를 포함하여 데이터를 삽입


## Data 조회

List는 pop을 통하여 조회(Queue처럼 사용시 편함)
Hash는 entries를 통해 데이터를 Map자료형을 가져옴
Set의 value 안의 값들은 member라고 정의, members를 통해 해당 Key의 값들을 set자료형으로 가져올 수 있음
ZSet은 range를 통해 정해진 Rank범위의 데이터를 가져올수 있음. (Score를 기준으로 오름차순으로 데이터를 정렬)

