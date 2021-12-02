# cloud-kafka

## Kafka란?

- 여러 대의 분산 서버에서 대량의 데이터를 처리하는 분산 메시징 시스템
- Pub-Sub  모델의 `메시지 큐`
- 오픈소스 솔루션

## Kafka 목적?사용 이유?

Kafka는 대량의 데이터를 **높은 처리량**과  **실시간**으로 다룰 수 있는 제품

- 확장성 : **여러 서버로 확장 구성** 할 수 있기 때문에 데이터의 양에 따라 시스템 확장이 가능
- 영속성 : **수신한 데이터를 디스크에 유지**할 수 있기 때문에 언제든지 데이터를 읽을 수 있다
- 유연성 : **연계할 수 있는 제품이 많기** 때문에 제품이나 시스템을 연결하는 허브 역할을 할 수 있다
- 신뢰성 : **메시지 전달 보증**을 하므로 데이터 분실 걱정을 하지 않아도 된다

## Kafka 아키텍처

- pub/sub 구조

![image](https://user-images.githubusercontent.com/74949294/144345941-8a8680b3-24cb-4a6d-9e66-8532d2485fd8.png)

![image](https://user-images.githubusercontent.com/74949294/144345955-3502e21d-054e-4414-92a3-7796205cc5f8.png)

## Kafka 구성요소

가장 기본적인 구성 요소들만 소개합니다. 

- Topic, Partition ( + Replication )
- Broker
- Producer
- Consumer

### Topic

Topic을 말하기 전 Message에 대해 간단하게만 말하겠습니다. 

Kafka Message는 key, value로 구성되는데, 카프카 브로커 내부 어느 위치에 저장될 지에 대한 값과 전달하고자 하는 내용이 담깁니다. 

메시지는 직렬화/역직렬화 되기 때문에 String이나 JSON, 특정 객체 등 다양한 타입으로 송수신 할 수 있습니다. 

Topic은 메시지를 구분하는 논리적 단위입니다. 쉽게 말하면 메시지 카테고리입니다. 

예를 들어 주문에 관한 메시지를 발행, 소비하기 위해 우리는 Order라는 Topic을 생성 후 이 Topic을 기준으로 메시지를 발행, 소비할 수 있습니다. 

### Partition

 Kafka Topic을 기준으로 발행되는 메시지들은 브로커 내부의 물리적인 단위인 파티션으로 분산 됨.

즉, 메시지는 Topic으로 분산 되고, Topic은 여러 개의 파티션으로 구성되어 있다. 

파티션은 Kafka Broker 즉 Kafka 서버를 클러스터 구성했을 경우 broker에 균등하게 분산하도록 설계되어 있습니다. (즉, 각 브로커들이 비슷한 수준의 리소스를 사용하도록 합니다. )

조금 헷갈릴 수 있는데, Topic이 여러 파티션으로 분리되어 있는 것이 맞지만 포함하고 있는 것은 아닙니다. 

![image](https://user-images.githubusercontent.com/74949294/144345995-e0005332-d893-4ee0-a4c0-29d5f50f5888.png)

**Replication**

Topic을 생성할 때 Kafka는 높은 가용성을 위해 Replication을 제공합니다.

Replication은 각 Topic의 Partition 들을 kafka cluster 내의 다른 broker들로 복제하는 것입니다.

Partition은 하나의 Leader와 0개 이상의 Follower로 나뉘고 ISR라는 Replication Group을 형성합니다. 

그림이 이해하기 더 쉬우니 그림으로 설명하겠습니다.

> Topic : test
partition 개수 : 3
replication factor : 1
> 

![image](https://user-images.githubusercontent.com/74949294/144346007-421a71c7-0c99-4fca-b3f2-aab1e1dbad81.png)

위 그림은 복제 없이 test라는 이름의 topic을 3개의 partition과 복제 없이 생성한 그림입니다. 

만약 replication factor를 1이 아닌 3으로 하게 된다면 아래와 같이 각 파티션들이 2개씩 복제되어 생성되며 각 파티션은 하나의 leader 파티션과 follower 파티션으로 구성됩니다.

![image](https://user-images.githubusercontent.com/74949294/144346028-9c8e6b94-4781-49c5-ac17-499f6ec30d35.png)

모든 메세지 read/write는 Leader Partition에서만 할 수 있습니다. 


### Broker

일반적으로 'Kafka'라고 불리는 시스템으로 **카프카 서버**라고도 불립니다. 

브로커 내부에 여러 토픽이 생성되며, 이러한 토픽들에 의해 생성된 파티션들이 보관하는 데이터에 대해 분산 저장을 하거나 장애 발생 시 안전하게 데이터를 사용할 수 있도록 도와준다. 

즉, 카프카가 지향하는 **pub/sub 모델 패턴에서 메시지 관리**를 담당한다.

- 일반적으로 Kafka라고 불리는 시스템. **카프카 서버**라고 함
- Kafka Cluster는 여러대의 broker(server)로 구성
- **pub/sub 모델 패턴에서 메시지 관리를 담당**
- Broker 내부에 여러 Topic이 생성되며, Topic에 의해 생성된 파티션들이 보관하는 데이터에 대해 분산 저장을 하거나 장애 발생 시 안전하게 데이터를 사용할 수 있도록 도와줌
    - 장애 발생 시 partition 단위로 failover
- Controller Broker에 의해 Partition의 Leader 선정, Topic 생성, 복제본 관리


### Producer

- Topic에 메시지(데이터)를 write
- partitioner에 의해 어떤 broker와 어떤 partition에 데이터를 write할 지 알고 있다.
    - 메시지를 전송할 때 어떤 파티션으로 전송해야 할 지 결정해주는 클래스
    

**메시지 전달 과정**

- 직렬화 (Serializer)
- 파티셔닝 (Partitioning)
- 메시지 배치 (Record Accumulator)
- 압축 (Compression)
- 전달 (Sender)

### Consumer

- Topic에 있는 메시지(데이터)를 read (polling 구조)
- consumer offset에 마지막으로 읽어온 partition의 offset을 저장
    - Consumer application이 중단되었다가 다시 시작될 경우 어디서부터 메시지를 읽어야 하는지 알 수 있음
    - Consumer 상태와 관계 없이 안정적인 메시지 구독이 가능
- Consumer Group을 통해 병렬 구성하여 처리 가능

## Kafka Cluster 구성

예를 들어 브로커 3개로 클러스터 구성을 할 때에 3대의 서버를 사용할 수도 있지만, 1대의 서버를 포트를 다르게 하여 구성할 수도 있습니다. 

**zookeeper 설정**

`{카프카 위치}/config/zookeeper.properties` 설정

```bash
# the directory where the snapshot is stored.
dataDir=/tmp/zookeeper
# the port at which the clients will connect
clientPort=2181
# disable the per-ip limit on the number of connections since this is a non-production config
maxClientCnxns=0

# 팔로워가 리더와 초기에 연결하는 시간에 대한 타임아웃
initLimit=5
# 팔로워가 리어와 동기화 하는데에 대한 타임 아웃. 해당 시간 안에 리더와 팔로워가 동기화 되지 않는 다면 제거된다
syncLimit=2

server.1=192.168.120.130:2888:3888
server.2=192.168.120.131:2888:3888
server.3=192.168.120.132:2888:3888
```

**서버 설정**

- [broker.id](http://broker.id) - 같은 카프카 클러스터에서 현재 브로커를 식별하기 위한 숫자.

```bash
$ mkdir /tmp/zookeeper & echo 1 > tmp/zookeeper/myid
$ mkdir /tmp/zookeeper & echo 2 > tmp/zookeeper/myid
$ mkdir /tmp/zookeeper & echo 3 > tmp/zookeeper/myid
```

```bash
# 서버 192.168.120.131
broker.id = 2
listeners = PLAINTEXT://9092
advertised.listeners=PLAINTEXT://192.168.120.131:9092
zookeeper.connect=192.168.120.130, 192.168.120.131, 192.168.120.132
```

## Kafka 사용법(환경 설정)

[Kakfa 설치 및 환경 구성](https://www.notion.so/Kakfa-87f1e072fb62456f9667a4db047a9c5a)

## 샘플 프로젝트 구조

![image](https://user-images.githubusercontent.com/74949294/144346080-d669baf5-af1a-4c4b-b46a-d8d8dfbb64c2.png)

**Multi-Module 프로젝트**

- cloudit6-kafka : root 프로젝트
- kakfa-common : 공통적으로 사용되는 도메인 및 공통 코드
- ace-admd : api 서버 역할 모듈
- ace-virtd : 실제 가상화 기능 수행 모듈

```
kafka-common
	ㄴ KafkaCommonApplication.java
	ㄴ config
	ㄴ domain
	ㄴ dto
	ㄴ mapper
	ㄴ repository
	ㄴ utils

ace-admd
	ㄴ AceAdmdApplication.java
	ㄴ config
	ㄴ controller
	ㄴ receiver
	ㄴ service

ace-virtd
	ㄴ AceVirtdApplication.java
	ㄴ ace
	ㄴ config
```

![image](https://user-images.githubusercontent.com/74949294/144346104-5bf4804a-9127-4063-93de-d4198edb3cc3.png)

### Spring Kafka vs Apache Kafka

- 모두가 흔히 말하는 카프카는 Apache kakfa인데 spring boot에서 apache kafka를 좀 더 편리하게 사용할 수 있도록 한 라이브러리이다.
- spring kafka를 사용하면 apache kafka를 사용할 수 있다.

## 샘플 프로젝트 시현

- Topic에 대한 설정이 별도로 없어도 `auto.create.topic.enable` 설정을 통해 자동으로 생성 된다. 해당 설정은 default 값이 `true`이다

### kafka-common

**config**

- KafkaProducerConfig - producer 공통 설정(KafkaTemplate을 빈으로 등록)
    - ProducerFactory : 각 message 종류 별로 message를 어디로 보낼지, 어떠한 방법으로 처리할지 설정
    - KafkaTemplate :  topic에 message를 보낼 수 있는 편리한 메서드 제공
- KafkaConsumerConfig - consumer 공통 설정
    - ConsumerFactory : 각 message 종류 별로 message를 어디서 받고 어떠한 방법으로 처리할지 설정
    - ConsurentMessageListenerContainer : 멀티 스레드 방식의 Consumer (Consumer 설정)
    

**domain**

- entity 정의

**dto**

- dto 정의

**mapper**

- entity와 dto 매핑 클래스 정의
- mapstruct 사용

**repository**

- repository 구현

**utils**

- 공통 로직
    - ActionQueueSender - message 전송 로직 : 전송될 QueueAction을 String으로 변환 후 broker에 전송
    - QueueAction - Action 객체 정의
    
