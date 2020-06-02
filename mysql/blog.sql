/*
Navicat MySQL Data Transfer

Source Server         : 阿里云Docker
Source Server Version : 50647
Source Host           : 39.107.13.154:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50647
File Encoding         : 65001

Date: 2020-06-02 18:46:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for artical
-- ----------------------------
DROP TABLE IF EXISTS `artical`;
CREATE TABLE `artical` (
  `artical_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL DEFAULT '1',
  `status` int(11) DEFAULT '0',
  `post_time` date DEFAULT NULL,
  `view_count` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT '1',
  `artical_title` varchar(255) DEFAULT NULL,
  `artical_introduce` varchar(255) DEFAULT NULL,
  `pic_introduce_img_url` varchar(255) DEFAULT NULL,
  `artical_content` longtext,
  `is_deny_comment` int(11) DEFAULT '0',
  `is_lock` int(11) DEFAULT '0',
  `is_submit_top` int(11) DEFAULT '0',
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`artical_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of artical
-- ----------------------------
INSERT INTO `artical` VALUES ('9', '2', '1', '2020-05-26', '0', '0', 'Java面试题', '这是我整理的Java面试题，仅供参考~！', 'http://image.tinx.top/2020/5/27/articleTitle/time.jpg', '# Java面试测试\n\n---\n\n## try catch问题\n\n> 假如说在try中return了，那么finally中的代码会执行吗？\n>\n> 会执行，执行完finally中的代码后return\n\n## synchornized和lock/unlock\n\n> + synchronized 同步方法\n>\n>   其他线程如果想访问一个线程正在执行的同步方法，就会进入一个等待的状态，等这个线程释放锁以后，其他线程就会进行资源竞争，释放锁有两个条件：\n>\n>   1. 该线程执行完毕对应的代码块，自动释放锁\n>   2. 在执行该代码块中发生了异常，JVM会自动释放锁\n>\n> + lock\n>\n>   唯一的实现类是ReentranLock，这个锁是可重入锁，该锁支持两种模式：\n>\n>    1. 公平锁\n>\n>       当线程A获取访问该对象，获取到锁以后，此时内部存在一个计数器num+1,其他线程想访问该对象，就会进行排队等待，直到线程A释放锁(num=0)，此时会唤醒处于等待状态的线程进行获取锁的过程，一直循环。\n>\n>       如果线程A想再次尝试获取该对象的锁时，会检查该对象锁已经释放/被占用，如果被占用，会做一次是否为当前线程占用锁的判断，如果是内部计数器num+1,并且不需要进入等待队列，而是直接回去当前锁\n>\n>    2. 非公平锁\n>\n>       当线程A在释放锁以后，等待对象的线程会进行资源竞争，竞争成功的线程将获取该锁，其他线程继续睡眠\n>\n>   \n>\n>   公平锁是严格的以队列FIFO的方式进行锁的竞争，但是非公平锁是无序的锁竞争，刚释放锁的线程很大程度上能比较快的获取到锁，队列中的线程只能等待，所以非公平锁可能会有“饥饿”的问题。但是重复的锁获取能减小线程之间的切换，而公平锁则是严格的线程切换，这样对操作系统的影响是比较大的，所以非公平锁的吞吐量是大于公平锁的，这也是为什么JDK将非公平锁作为默认的实现。\n>\n>   下面是Lock锁的方法：\n>\n>   ​	![](https://img2018.cnblogs.com/blog/1222507/201906/1222507-20190603210347151-1972345480.png)\n\n## 集合\n\n> 集合可以大致分为两个总类:\n>\n> + Collection\n>\n>   + List // 有序，可以重复的集合，查询效率高，插入删除效率低，会引起元素位置的改变\n>\n>     + ArrayList // 底层是数组，在内存中是一整块区域，对于插入更新效率不高，因为底层是数组\n>     + LinkedList // 底层是链表，在内存中不是一整块区域，对于插入更新效率高，但是查询效率低\n>     + **Vector** // 是多线程安全的，内部包裹syncchronized，但是这样效率低\n>\n>   + Set // 无序、不可重复的集合，查询效率低，删除和插入效率高，插入和删除不会引起元素位置改变\n>\n>     + HashSet //能够最快的获取集合中的元素，效率非常高（以空间换时间）\n>     + TreeSet //会自动排序\n>\n>     ``` java\n>     // Collection 集合类下面的方法\n>     1、boolean add(Object o)：向集合里添加一个元素，成功添加则返回true。 \n>     2、boolean addAll(Collection c)：把集合c里的所有元素添加到指定集合里，成功添加，返回true。\n>     3、void clear()：清除集合里的所有元素，将集合长度变为0。\n>     5、boolean contains(Object o)：判断集合中是否包含指定元素 \n>     6、boolean containsAll(Collection c)：判断集合中是否包含集合c中的所有元素。\n>     7、boolean isEmpty()：判断集合是否为空。为空的时候返回true，否则返回false\n>     8、boolean remove(Object o)：删除集合中指定的o元素，当集合中含有多个o元素的时候，只删除第一个符合条件的元素，并将返回true \n>     9、boolean removeAll(Collection c)：从集合中删除集合c里包含的所有元素（相当于调用该方法的集合 - 集合c）\n>     10、boolean retainAll(Collection c)：从集合中删除集合c里不包含的元素（也就是把调用该方法的集合变成该集合和集合c的交集）（交集方法）。 \n>     11、int size()：返回集合里元素的个数 \n>     12、Object[] toArray()：该方法把一个集合转换为数组，所有的集合元素变成对应的数组元素\n>     ```\n>\n>     \n>\n> + Map // 具有映射关系的集合 Key-Value\n>\n>   + HashMap // 使用哈希表结构，非同步的，效率比HashTable高，允许空键值\n>   + TreeMap \n>   + HashTable // 同步的，不允许空键值\n>\n>   ``` java\n>   1. Map提供了一种映射关系，其中的元素是以键值对（key-value）的形式存储的，能够实现根据key快速查找value；\n>   2. Map中的键值对以Entry类型的对象实例形式存在；\n>   建（key值）不可重复，value值可以重复，一个value值可以和很多key值形成对应关系，每个建最多只能映射到一个值。\n>   3. Map支持泛型，形式如：Map<K,V>\n>   4. Map中使用put(K key,V value)方法添加\n>   // Map接口下的方法\n>   1	void clear( )从此映射中移除所有映射关系（可选操作）。\n>   2	boolean containsKey(Object k)如果此映射包含指定键的映射关系，则返回 true。\n>   3	boolean containsValue(Object v)如果此映射将一个或多个键映射到指定值，则返回 true。\n>   4	Set entrySet( )返回此映射中包含的映射关系的 Set 视图。\n>   5	boolean equals(Object obj)比较指定的对象与此映射是否相等。\n>   6	Object get(Object k)返回指定键所映射的值；如果此映射不包含该键的映射关系，则返回 null。\n>   7	int hashCode( )返回此映射的哈希码值。\n>   8	boolean isEmpty( )如果此映射未包含键-值映射关系，则返回 true。\n>   9	Set keySet( )返回此映射中包含的键的 Set 视图。\n>   10	Object put(Object k, Object v)将指定的值与此映射中的指定键关联（可选操作）。\n>   11	void putAll(Map m)从指定映射中将所有映射关系复制到此映射中（可选操作）。\n>   12	Object remove(Object k)如果存在一个键的映射关系，则将其从此映射中移除（可选操作）。\n>   13	int size( )返回此映射中的键-值映射关系数。\n>   14	Collection values( )返回此映射中包含的值的 Collection 视图。\n>   ```\n>\n>   排序要实现Compareable接口，重写compare方法\n\n## ArrayList和Vector的区别\n\n>1. Vector是多线程安全的，线程安全就是说多线程访问同一代码，不会产生不确定的结 果。而ArrayList不是，这个可以从源码中看出，Vector类中的方法很多有synchronized 进行修饰，这样就导致了Vector在效率上无法与ArrayList相比； \n>\n>2. 两个都是采用的线性连续空间存储元素，但是当空间不足的时候，两个类的增加方式是 不同。 \n>\n>3. Vector可以设置增长因子，而ArrayList不可以。 \n>\n>4. Vector是一种老的动态数组，是线程同步的，效率很低，一般不赞成使用。 \n\n**适用场景分析：** \n\n1. Vector是线程同步的，所以它也是线程安全的，而ArrayList是线程异步的，是不安全的。如果不考虑到线程的安全因素，一般用ArrayList效率比较高。 \n\n2. 如果集合中的元素的数目大于目前集合数组的长度时，在集合中使用数据量比较大的数据，用Vector有一定的优势\n\n## Map的遍历\n\n``` java\n1. 最常用的\nMap <String,String>map = new HashMap<String,String>();\nmap.put(\"熊大\", \"棕色\");\nmap.put(\"熊二\", \"黄色\");\nfor(Map.Entry<String, String> entry : map.entrySet()){\n    String mapKey = entry.getKey();\n    String mapValue = entry.getValue();\n    System.out.println(mapKey+\":\"+mapValue);\n}\n\n2. 查询具体的键或值\nMap <String,String>map = new HashMap<String,String>();\nmap.put(\"熊大\", \"棕色\");\nmap.put(\"熊二\", \"黄色\");\n//key\nfor(String key : map.keySet()){\n    System.out.println(key);\n}\n//value\nfor(String value : map.values()){\n    System.out.println(value);\n}\n3. 使用Iterator对象进行遍历\nIterator<Entry<String, String>> entries = map.entrySet().iterator();\nwhile(entries.hasNext()){\n    Entry<String, String> entry = entries.next();\n    String key = entry.getKey();\n    String value = entry.getValue();\n    System.out.println(key+\":\"+value);\n}\n4. 通过键查找值\nfor(String key : map.keySet()){\n    String value = map.get(key);\n    System.out.println(key+\":\"+value);\n}\n```\n\n## Maven的生命周期与命令\n\n> Maven的生命周期就是对所有的构建过程进行抽象和统一。包含了项目的清理、初始化、编译、测试、打包、集成测试、验证、部署和站点生成等几乎所有的构建步骤。\n>\n> 相关命令:\n>\n> mvn install\n>\n> mvn clean\n>\n> mvn package\n>\n> mvn compile\n>\n> mvn install -Dmaven.test.skip=true\n\n## 集合框架HashMap\n\n> 哈希冲突的解决方案有多种:开放定址法（发生冲突，继续寻找下一块未被占用的存储地址），再散列函数法，链地址法，而HashMap即是采用了**链地址法**，也就是**数组+链表+红黑树**的方式。\n\n## 遍历集合的几种办法\n\n1. for\n2. forEach\n3. Stream\n4. Iterator\n\n## 多线程/线程池\n\n> 线程实现的集中方式：\n>\n> 1. 继承Thread类，重写run方法\n> 2. 实现Runable接口，重写run方法\n> 3. 实现Callable接口，重写call方法，实现时使用FutureTask来包装实现Callable的实现类，来进行start\n> 4. 使用线程池获取线程\n\n### 线程的生命周期\n\n1. 新建\n\n   Thread t = new Thread()\n\n2.  就绪\n\n   t.strat();\n\n3. 运行\n\n   t.run()\n\n4. 阻塞\n\n   t.sleep()/t.stop()/t.wait()\n\n5. 死亡\n\n   t.stop()\n\n## 创建线程池的几种方式\n\n> newFixedThreadPool(int nThreads)\n> 创建一个固定长度的线程池，每当提交一个任务就创建一个线程，直到达到线程池的最大数量，这时线程规模将不再变化，当线程发生未预期的错误而结束时，线程池会补充一个新的线程\n>\n> newCachedThreadPool()\n> 创建一个可缓存的线程池，如果线程池的规模超过了处理需求，将自动回收空闲线程，而当需求增加时，则可以自动添加新线程，线程池的规模不存在任何限制\n>\n> newSingleThreadExecutor()\n> 这是一个单线程的Executor，它创建单个工作线程来执行任务，如果这个线程异常结束，会创建一个新的来替代它；它的特点是能确保依照任务在队列中的顺序来串行执行\n>\n> newScheduledThreadPool(int corePoolSize)\n> 创建了一个固定长度的线程池，而且以延迟或定时的方式来执行任务，类似于Timer。\n\n## 锁的问题\n\n### 乐观锁\n\n> + 在数据库方面\n>\n>   > 乐观锁假设认为数据一般情况下不会造成冲突，所以在数据进行提交更新的时候，才会正式对数据的冲突与否进行检测，如果发现冲突了，则让返回用户错误的信息，让用户决定如何去做\n>   >\n>   > 实现乐观锁的几种方式：\n>   >\n>   > ```java\n>   > 1. 使用版本号来实现乐观锁\n>   > ```\n>   >\n>   > > 使用数据版本（Version）记录机制实现，这是乐观锁最常用的一种实现方式。何谓数据版本？即为数据增加一个版本标识，一般是通过为数据库表增加一个数字类型的 “version” 字段来实现。当读取数据时，将version字段的值一同读出，数据每更新一次，对此version值加一。当我们提交更新的时候，判断数据库表对应记录的当前版本信息与第一次取出来的version值进行比对，如果数据库表当前版本号与第一次取出来的version值相等，则予以更新，否则认为是过期数据。用下面的一张图来说明：\n>   > >\n>   > > ![](https://images2017.cnblogs.com/blog/1075594/201712/1075594-20171224120703021-326642906.png)\n>   > >\n>   > > 如上图所示，如果更新操作顺序执行，则数据的版本（version）依次递增，不会产生冲突。但是如果发生有不同的业务操作对同一版本的数据进行修改，那么，先提交的操作（图中B）会把数据version更新为2，当A在B之后提交更新时发现数据的version已经被修改了，那么A的更新操作会失败。\n>   > >\n>   > > b.时间戳机制，同样是在需要乐观锁控制的table中增加一个字段，名称无所谓，字段类型使用时间戳（timestamp）, 和上面的version类似，也是在更新提交的时候检查当前数据库中数据的时间戳和自己更新前取到的时间戳进行对比，如果一致则OK，否则就是版本冲突。\n>   >\n>   > ``` java\n>   > 2. 使用条件限制来实现乐观锁\n>   > ```\n>   >\n>   > > 这个适用于只更新是做数据安全校验，适合库存模型，扣份额和回滚份额，性能更高。这种模式也是目前我用来锁产品库存的方法，十分方便实用。\n>   \n>   + 在java方面\n>   \n>   > 使用CAS原子类实现\n>   >\n>   > CAS是一种更新的原子操作，比较当前值跟传入值是否一样，一样则更新，否则失败\n>\n\n乐观锁：比较适合读取操作比较频繁的场景，如果出现大量的写入操作，数据发生冲突的可 能性就会增大，为了保证数据的一致性，应用层需要不断的重新获取数据，这样会增加大量 的查询操作，降低了系统的吞吐量\n\n### 悲观锁\n\n> + 在数据库方面\n>\n> > 关闭autocommit 自动提交事务\n> >\n> > 然后使用for update来实现悲观锁\n>\n> + 在java方面\n>\n> > synchornized包裹的方法/代码块\n\n本质来说，就是悲观锁认为总会有人抢我的。 \n\n乐观锁就认为，基本没人抢。 \n\n## Cookie和Session\n\n> 区别：\n>\n> + Cookie\n>\n>   1. 存在于客户端中，可以被修改\n>   2. 最大只支持4KB\n>   3. 浏览器在每个网站的Cookie的个数有限\n>   4. 网站的基本配置\n>\n> + Session\n>\n>   1. 存在于服务器，客户不能进行修改\n>   2. 无最大限制\n>   3. 依赖于Cookie来存储SeessionID\n>\n>   假如说Cookie被禁用了，则Session还能用吗？\n>\n>   ​	可以！可以对url进行重写，来实现Cookie的部分功能', '0', '0', '0', '1');
INSERT INTO `artical` VALUES ('10', '2', '1', '2020-05-31', '0', '0', 'JDK8 学习', '学习JDK8的一些小笔记，还算是精髓吧，欢迎交流学习', 'http://image.tinx.top/2020/6/1/articleTitle/cartoon.jpg', '# jdk8新特性学习\n\n## Lambda表达式\n\n> 减少代码量，使之简洁\n>\n> 函数式接口\n>\n> Lambda是一个匿名函数，我们可以把Lambda表达式理解为是一段可以传递的代码\n\n### 语法格式\n\n1. 无参，无返回值\n\n   ``` java\n   Runnable r = () -> System.out.println(\"我爱你\");\n   ```\n\n2. 有参，有返回值\n\n   ``` java\n   Comparator<Integer> c = (o1,o2) -> Integer.compare(o1,o2);\n   System.out.println(c.compare(1,2));\n   ```\n\n3. 有一个参数，无返回值\n\n``` java\nConsumer<String> cc = (String s) -> System.out.println(s);\ncc.accept(\"wills is awsome man !\");\n```\n\n5. 类型推断\n\n``` java\nConsumer<String> cc1 = (s) -> System.out.println(s);\ncc1.accept(\"wills is awesome man !\");\n```\n\n6. 如果只需要一个参数，参数的小括号可以省略\n\n``` java\nConsumer<String> cc1 = s -> System.out.println(s);\ncc1.accept(\"wills is awesome man !\");\n```\n\n7. 需要两个或以上的参数，多条执行语句，并且可以有返回值\n\n``` java\nComparator<Integer> ccc1 = (o1,o2) -> {\n            System.out.println(o1);\n            System.out.println(o2);\n            return o1.compareTo(o2);\n};\nSystem.out.println(ccc1.compare(1,2));\n```\n\n8. 当Lambda只有一条语句时，reuturn 与大括号语句可以省略\n\n   > 详情见6\n\n## 函数式（Functional）接口\n\n> Lambda 表达式的本质: 作为函数式接口的实例\n>\n> 在接口类中使用@FunctionalInterface即可使用lambda表达式\n\n+ 创建一个Functional借口\n\n``` java\n@FunctionalInterface\npublic interface WillsFunctionalInteface {\n\n    void show(String s);\n}\n```\n\n+ 调用\n\n  ``` java\n  WillsFunctionalInteface w = s -> System.out.println(s);\n  w.show(\"wills show yourself\");\n  ```\n\n## jdk8内置四大核心函数式接口\n\n| 函数式接口                      | 参数类型 | 返回类型 | 用途                                                         |\n| ------------------------------- | -------- | -------- | ------------------------------------------------------------ |\n| Consumer<T><br />消费类型接口   | T        | void     | 对类型为T的对象应用操作，包含方法：<br />void accept(T t)    |\n| Supplier<T><br />供给类型接口   | null     | T        | 返回类型为T的对象，包含方法 T get()                          |\n| Function<T,R><br />函数类型接口 | T        | R        | 对类型为T的对象应用操作，并返回结果。结果是R类型的对象，包含方法：R apply(T t) |\n| Predicate<T><br />断定型接口    | T        | boolean  | 确定类型为T的对象是否满足约束，并返回boolean值。包含方法：boolean test(T t) |\n\n+ Consumer\n\n``` hava\n@Test\npublic void t3(){\n    happyTime(2.5,d -> {\n        System.out.println(\"今天学习了好久，去买了泡面吃了，价格为：\" + d);\n    });\n}\n\npublic void happyTime(double d,Consumer<Double> con){\n    con.accept(d);\n}\n```\n\n+ Supplier\n\n  ``` java\n  Emp e = new Emp(1,\"wills\",\"boss\");\n  Supplier<String> s = () -> e.getName();\n  System.out.println(s.get());\n  ```\n\n+ Function \n\n  ``` java\n  // Double为传入参数，Long为返回参数\n  Function<Double,Long> f = Math::round;\n  System.out.println(f.apply(2.5));\n  ```\n\n+ Predicate\n\n  ``` java\n  @Test\n  public void t4(){\n   	List<String> list = new ArrayList<>();\n   	list.add(\"1\");\n   	list.add(\"2\");\n   	list.add(\"3\");\n   	list.add(\"4\");\n   	String filterString = filterString(list, s -> s.contains(\"1\"));\n   	System.out.println(filterString);\n  }\n  \n  public String filterString(List<String> list, Predicate<String> pre){\n      for (String s : list) {\n          if(pre.test(s)){\n              return \"yes\";\n          }\n      }\n      return null;\n  }\n  ```\n\n## 方法引用与构造器引用\n\n> 1. 当要传递给Lambda体的操作，已经有了实现的方法了，可以使用方法引用\n> 2. 方法引用本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例，所以方法引用，也是函数式接口的实例\n> 3. 方法引用的使用要求:要求接口中的抽象方法的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同\n>\n> 有以下三种使用情况：\n>\n> > 对象::实例方法名（非静态方法）\n> >\n> > 类::静态方法名\n> >\n> > 类::实例方法名(非静态方法)\n\n+ 对象::实例方法名\n\n``` java\nComparator<Integer> c1 = Integer::compare;\nSystem.out.println(c1.compare(2,3));\n\n// 使用方法引用形式改变Supplier\nSupplier<String> ss = e :: getName;\nSystem.out.println(ss.get());\n```\n\n+ 类::静态方法名\n\n``` java\nFunction<Double,Long> f = Math::round;\nSystem.out.println(f.apply(2.5));\n```\n\n+ 类::实例方法名(非静态方法)\n\n``` java\nomparator<Integer> c = Integer::compareTo;\nSystem.out.println(c.compare(1, 2));\n\nBiPredicate<Integer,Integer> b = Integer::equals;\nSystem.out.println(b.test(1, 2));\n\nEmp e = new Emp(1,\"wills\",\"boss\");\nFunction<Emp,String> f = Emp::getName;\nSystem.out.println(f.apply(e));\n```\n\n+ 调用别的对象的方法\n\n``` java\nPrintStream ps = System.out;\nConsumer<String> c = ps ::println;\nc.accept(\"wills is nice man\");\n```\n\n### 构造器的使用\n\n---\n\n``` java\n// 调用Emp的空参构造器\nSupplier<Emp> s = Emp::new;\n// 调用Emp的有参构造函数\nFunction<String,Emp> s = Emp::new;\ns.apply(\"wills\")\n// 多参数的Emp构造函数调用 warning: 最多两个参数！！！\nBiFunction<String,String,Emp> b = Emp::new;\nSystem.out.println(b.apply(\"wills\", \"boss\"));\n```\n\n### 数组引用\n\n> 可以把数组看做是一个特殊的类，写法与构造器引用一致\n\n---\n\n``` java\n// 旧方式的引用 创建一个新的自定义长度的数组\nFunction<Integer,String[]> f = length -> new String[length];\nSystem.out.println(f.apply(5).length);\n// 数组调用的方式引用 创建一个新的自定义长度的数组\nFunction<Integer,String[]> ff = String[] :: new;\nSystem.out.println(ff.apply(10).length);\n```\n\n## 强大的Stream Api\n\n> Stream Api把真正的函数式编程风格引入到java中。\n>\n> 这是目前为止对java类库最好的补充，因为Stream API可以极大提供Java程序员生产力，让程序员写出高效率。干净、简洁的代码\n>\n> 使用Stream Api对集合数据进行操作，就类似于使用SQL执行的数据库查询\n>\n> Stream和	Collectio集合的区别：Collection是一种静态的内存数据结构，而Stream是有关计算的。前者是主要面向内存，存储在内存中，后者主要是面向CPU，通过CPU进行计算\n>\n> 注意：\n>\n> 1. Stream自己不会存储元素\n> 2. Stream不会改变源对象，相反，他们会返回一个持有结果的新Stream\n> 3. Stream操作是延迟执行的，这意味着他们会等到需要结果的时候才执行\n\n### Stream操作的三个步骤\n\n1. 创建Stream\n\n``` java\nList<Emp> emps = Emp.getEmps();\n// 顺序流\nStream<Emp> stream = emps.stream();\n// 并行流\nStream<Emp> parallelStream = emps.parallelStream();\n// 通过数组创建Stream\nList<Emp> emps = Emp.getEmps();\nEmp[] e = new Emp[]{emps.get(0),emps.get(1)};\nStream<Emp> stream = Arrays.stream(e);\n\n//通过stream的of 方法\nStream<Integer> stream = Stream.of(1, 2, 3, 4, 5);\n```\n\n2. 中间操作\n\n一个中间操作链，对数据源的数据进行处理\n\n<font color=red>注意！一旦执行终止操作，就不会再被使用！！！请看第2个limit示例，需要重新获取Stream流</font>\n\n``` java\n// 查询员工姓名为a的其中信息输出 filter\nEmp.getEmps().stream().filter(e -> e.getName()==\"a\").forEach(System.out::println);\n// limit 限制输出集合中的几个元素\nEmp.getEmps().stream().limit(2).forEach(System.out::println);\n// skip 跳过集合中的几个元素\nEmp.getEmps().stream().skip(2).forEach(System.out::println);\n// 去除重复元素\nEmp.getEmps().stream().distinct().forEach(System.out::println);\n// map 映射 将字符串数组中的元素编程大写\nList<String> list = Arrays.asList(\"a\", \"b\", \"c\", \"d\");\nlist.stream().map(str -> str.toUpperCase()).forEach(System.out::println);\n// 练习 输出id大于1的员工\nStream<Integer> s = Emp.getEmps().stream().map(Emp::getId);\ns.filter(i -> i >1).forEach(System.out::println);\n// 对于集合中包含别的集合的使用flatMap\n\n// 使用sorted来排序集合中的数据\nList<Integer> list = Arrays.asList(1, 42, 64, 12, -87, -789, -15, 0);\nlist.stream().sorted().forEach(System.out::println);\n// 定制比较器\nList<Emp> emps = Emp.getEmps();\nemps.stream().sorted((e1,e2) -> Integer.compare(e1.getId(),e2.getId())).forEach(System.out::println);\n```\n\n3. 终止操作\n\n一旦执行终止操作，就执行中间操作链，并产生结果。之后，<font color=red>就不会再被使用</font>\n\n``` java\n// allMatch 全部满足\n// anyMatch 部分满足\n// noneMatch 不满足\n// findFirst 返回第一个元素\n// findAny 返回当前流中的任意元素\n// count 返回流中元素的总个数\n// max min 返回最大/最小\n// forEach 内部迭代\n\n// reduce 规约   --reduce(额外的加值,Integer::sum) / reduce(Double::sum)\n\n// collect 用于给Stream做汇总的方法\n```\n\n## Optional<T>类\n\n> 最大化减少空指针异常\n>\n> // ofNullable\n>\n> // orElse', '0', '0', '0', '1');
INSERT INTO `artical` VALUES ('11', '2', '1', '2020-05-31', '0', '0', 'Java有趣的算法题', 'LeetCode 刷的简单算法题，会持续更新，敬请期待~！', 'http://image.tinx.top/2020/6/1/articleTitle/cartoon1.jpg', '# LeetCode刷题-Java\n\n## 查找字符串首次出现特定字符串的索引\n\n> 实现 strStr() 函数。\n>\n> 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。\n>\n> 示例 1:\n>\n> 输入: haystack = \"hello\", needle = \"ll\"\n> 输出: 2\n> 示例 2:\n>\n> 输入: haystack = \"aaaaa\", needle = \"bba\"\n> 输出: -1\n\n``` java\n// 调用内置函数indexOf()函数\npublic int strStr(String haystack, String needle) {\n    return haystack.indexOf(needle);\n}\n\n// 手动遍历字符串\npublic int strStr(String haystack, String needle) {\n  int L = needle.length(), n = haystack.length();\n  for (int start = 0; start < n - L + 1; ++start) {\n    if (haystack.substring(start, start + L).equals(needle)) {\n      return start;\n    }\n  }\n  return -1;\n}\n```\n\n', '0', '0', '0', '1');

-- ----------------------------
-- Table structure for base
-- ----------------------------
DROP TABLE IF EXISTS `base`;
CREATE TABLE `base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base_name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='这是后台的功能菜单';

-- ----------------------------
-- Records of base
-- ----------------------------
INSERT INTO `base` VALUES ('1', '后台首页', '/base', 'el-icon-truck', '0');
INSERT INTO `base` VALUES ('2', '文章管理', null, 'el-icon-document', '0');
INSERT INTO `base` VALUES ('3', '系统管理', null, 'el-icon-setting', '0');
INSERT INTO `base` VALUES ('4', '个人设置', null, 'el-icon-user', '0');
INSERT INTO `base` VALUES ('5', '其他管理', null, 'el-icon-refresh-right', '0');
INSERT INTO `base` VALUES ('6', '新建文章', '/articleWrite', 'el-icon-edit', '2');
INSERT INTO `base` VALUES ('7', '文章管理', '/articleManage', 'el-icon-folder-opened', '2');
INSERT INTO `base` VALUES ('9', '用户管理', '/userManage', 'el-icon-user', '3');
INSERT INTO `base` VALUES ('10', '角色管理', '/roleManage', 'el-icon-pie-chart', '3');
INSERT INTO `base` VALUES ('11', '权限查看', '/authView', 'el-icon-view', '3');
INSERT INTO `base` VALUES ('12', '附件管理', '/fileManage', 'el-icon-folder', '3');
INSERT INTO `base` VALUES ('13', '网站设置', '/websiteManage', 'el-icon-setting', '3');
INSERT INTO `base` VALUES ('14', '操作日志', '/log', 'el-icon-date', '3');
INSERT INTO `base` VALUES ('15', '信息修改', '/changePwd', 'el-icon-lock', '4');
INSERT INTO `base` VALUES ('16', '页面管理', '/pageManage', 'el-icon-s-grid', '5');
INSERT INTO `base` VALUES ('17', '评论管理', '/commentManage', 'el-icon-s-check', '5');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `category_url` varchar(255) NOT NULL,
  `icon` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '首页', 'main', 'el-icon-s-home');
INSERT INTO `category` VALUES ('2', 'Java', 'Java', 'el-icon-user-solid');
INSERT INTO `category` VALUES ('3', 'Node', 'Node', 'el-icon-s-flag');
INSERT INTO `category` VALUES ('4', 'Python', 'Python', 'el-icon-star-on');
INSERT INTO `category` VALUES ('5', 'Vue', 'Vue', 'el-icon-s-promotion');
INSERT INTO `category` VALUES ('6', '心情', 'Emotion', 'el-icon-hot-water');
INSERT INTO `category` VALUES ('7', '生活', 'Life', 'el-icon-s-shop');
INSERT INTO `category` VALUES ('8', '旅行', 'Travel', 'el-icon-camera-solid');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `comment_content` varchar(255) NOT NULL,
  `post_time` datetime NOT NULL,
  `article_id` int(11) NOT NULL DEFAULT '0',
  `post_ip` varchar(255) DEFAULT '',
  `status` int(255) DEFAULT '1',
  `user_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('4', '0', '博主的博客做的太棒了！', '2020-04-10 12:03:45', '3', '123.130.171.211', '1', 'Wills');
INSERT INTO `comment` VALUES ('5', '0', '我是博主！', '2020-04-10 12:15:44', '3', '123.130.171.211', '1', 'Tinx');
INSERT INTO `comment` VALUES ('6', '0', '很棒哦', '2020-04-10 12:16:35', '3', '123.130.171.211', '1', 'Bob');
INSERT INTO `comment` VALUES ('7', '0', '受益匪浅！', '2020-05-27 08:56:53', '9', '39.86.242.35', '0', '乖乖的小虎');
INSERT INTO `comment` VALUES ('8', '0', '还不错哦，赞一个', '2020-05-27 08:57:07', '9', '39.86.242.35', '0', '你好吗？');
INSERT INTO `comment` VALUES ('9', '0', 'alert(\'abc\')', '2020-06-02 04:30:20', '11', '39.86.242.35', '0', '你好吗？');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(255) NOT NULL,
  `config_content` varchar(255) DEFAULT NULL,
  `config_type` varchar(255) NOT NULL DEFAULT 'text',
  `config_if_select` varchar(255) DEFAULT NULL,
  `config_if_multify` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------

-- ----------------------------
-- Table structure for construct
-- ----------------------------
DROP TABLE IF EXISTS `construct`;
CREATE TABLE `construct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `construct_name` varchar(255) NOT NULL,
  `construct_url` varchar(255) NOT NULL,
  `level` int(11) DEFAULT '0',
  `parent_id` int(11) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of construct
-- ----------------------------
INSERT INTO `construct` VALUES ('1', 'head', '/', '0', '0', '头部导航栏');
INSERT INTO `construct` VALUES ('2', 'carousel', '/', '0', '0', '轮播图');
INSERT INTO `construct` VALUES ('3', 'left', '/', '0', '0', '文章展示页面');
INSERT INTO `construct` VALUES ('4', 'right', '/', '0', '0', '小功能菜单');
INSERT INTO `construct` VALUES ('5', 'footer', '/', '0', '0', '底部版权信息');
INSERT INTO `construct` VALUES ('6', 'welcome', '/welcome', '1', '4', '欢迎信息');
INSERT INTO `construct` VALUES ('7', 'time', '/time', '1', '4', '时间展示');
INSERT INTO `construct` VALUES ('8', 'weather', '/weather', '1', '4', '天气信息');
INSERT INTO `construct` VALUES ('9', 'music', '/music', '1', '4', '音乐播放器');
INSERT INTO `construct` VALUES ('10', 'category', '/category', '1', '4', '文章分类');
INSERT INTO `construct` VALUES ('11', 'link', '/link', '1', '5', '友情链接');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) NOT NULL,
  `file_type` varchar(255) NOT NULL,
  `file_description` varchar(255) DEFAULT NULL,
  `file_key` varchar(255) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', 'http://image.tinx.top/2020/3/31/articleTitle/sea.jpg', 'image/jpeg', '2020/3/31/的文章简略图', '2020/3/31/articleTitle/sea.jpg');
INSERT INTO `file` VALUES ('2', 'http://image.tinx.top/2020/3/31/articleTitle/water.jpg', 'image/jpeg', '2020/3/31/的文章简略图', '2020/3/31/articleTitle/water.jpg');
INSERT INTO `file` VALUES ('3', 'http://image.tinx.top/2020/4/10/articleTitle/sun.jpg', 'image/jpeg', '2020/4/10/的文章简略图', '2020/4/10/articleTitle/sun.jpg');
INSERT INTO `file` VALUES ('4', 'http://image.tinx.top/2020/4/11/articleTitle/tree&water.jpg', 'image/jpeg', '2020/4/11/的文章简略图', '2020/4/11/articleTitle/tree&water.jpg');
INSERT INTO `file` VALUES ('5', 'http://image.tinx.top/2020/5/27/articleTitle/time.jpg', 'image/jpeg', '2020/5/27/的文章简略图', '2020/5/27/articleTitle/time.jpg');
INSERT INTO `file` VALUES ('6', 'http://image.tinx.top/2020/6/1/articleTitle/cartoon.jpg', 'image/jpeg', '2020/6/1/的文章简略图', '2020/6/1/articleTitle/cartoon.jpg');
INSERT INTO `file` VALUES ('7', 'http://image.tinx.top/2020/6/1/articleTitle/cartoon1.jpg', 'image/jpeg', '2020/6/1/的文章简略图', '2020/6/1/articleTitle/cartoon1.jpg');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` int(11) NOT NULL,
  `log_content` varchar(255) NOT NULL,
  `log_operationDate` datetime NOT NULL,
  `log_requireType` varchar(255) NOT NULL,
  `log_requireIp` varchar(255) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) NOT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `menu_parent` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '主页', '/', '0');
INSERT INTO `menu` VALUES ('2', '文章分类', '/', '0');
INSERT INTO `menu` VALUES ('3', 'JAVA', '/search/Java', '2');
INSERT INTO `menu` VALUES ('4', 'Cpp', '/search/Cpp', '2');
INSERT INTO `menu` VALUES ('5', 'Python', '/search/Python', '2');
INSERT INTO `menu` VALUES ('6', 'Golang', '/search/Go', '2');
INSERT INTO `menu` VALUES ('9', '杂谈', '/search/other', '0');
INSERT INTO `menu` VALUES ('10', '生活', '/other/life', '9');
INSERT INTO `menu` VALUES ('11', '旅行', '/other/travel', '9');
INSERT INTO `menu` VALUES ('12', '心情', '/other/emotion', '9');
INSERT INTO `menu` VALUES ('13', '学习', '/other/study', '9');
INSERT INTO `menu` VALUES ('14', '留言板', '/comments', '0');

-- ----------------------------
-- Table structure for menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `menu_permission`;
CREATE TABLE `menu_permission` (
  `id` int(11) NOT NULL,
  `menu_name` varchar(255) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_permission
-- ----------------------------

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `note_content` varchar(255) NOT NULL,
  `post_ip` varchar(255) NOT NULL,
  `note_post_time` datetime NOT NULL,
  PRIMARY KEY (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  `permission_description` varchar(255) DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '文章管理', '管理文章-写/审核/删除/暂停展示等', '0', '0');
INSERT INTO `permission` VALUES ('2', '评论管理', '管理评论-审核/删除等', '0', '0');
INSERT INTO `permission` VALUES ('3', '文件管理', '管理文件-上传/删除/下载等', '0', '0');
INSERT INTO `permission` VALUES ('4', '用户管理', '管理用户', '0', '0');
INSERT INTO `permission` VALUES ('5', '缓存管理', '管理缓存', '0', '0');
INSERT INTO `permission` VALUES ('6', '图表分析', '图表分析管理', '0', '0');
INSERT INTO `permission` VALUES ('7', '文章写作', '书写文章', '1', '1');
INSERT INTO `permission` VALUES ('8', '文章管理', '增删改查文章', '1', '1');
INSERT INTO `permission` VALUES ('9', '管理评论', '管理评论', '1', '2');
INSERT INTO `permission` VALUES ('10', '上传信息配置', '配置七牛云', '1', '3');
INSERT INTO `permission` VALUES ('11', '删除上传的文件', '删除七牛云空间内文件', '1', '3');
INSERT INTO `permission` VALUES ('12', '管理用户', '用户的基本管理', '1', '4');
INSERT INTO `permission` VALUES ('13', '文章缓存管理', '管理文章缓存', '1', '5');
INSERT INTO `permission` VALUES ('14', 'Redis缓存管理', 'Redis缓存管理', '1', '5');
INSERT INTO `permission` VALUES ('15', 'ElasticSearch缓存管理', 'ElasticSearch缓存管理', '1', '5');
INSERT INTO `permission` VALUES ('16', '文章图表', '文章图表', '1', '6');
INSERT INTO `permission` VALUES ('17', '评论图表', '评论图表', '1', '6');
INSERT INTO `permission` VALUES ('18', '文件图表', '文件图表', '1', '6');
INSERT INTO `permission` VALUES ('19', '用户图表', '用户图表', '1', '6');
INSERT INTO `permission` VALUES ('20', '新增上传文章图表', '新增上传文章图表', '2', '16');
INSERT INTO `permission` VALUES ('21', '文章分类图表', '文章分类图表', '2', '16');
INSERT INTO `permission` VALUES ('22', '评论分析图表', '评论分析图表', '2', '17');
INSERT INTO `permission` VALUES ('23', '文件分析图表', '文件分析图表', '2', '18');
INSERT INTO `permission` VALUES ('24', '用户分析图表', '用户分析图表', '2', '19');

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role` (
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role` VALUES ('1', '1');
INSERT INTO `permission_role` VALUES ('1', '2');
INSERT INTO `permission_role` VALUES ('2', '1');
INSERT INTO `permission_role` VALUES ('3', '1');
INSERT INTO `permission_role` VALUES ('4', '1');
INSERT INTO `permission_role` VALUES ('4', '2');
INSERT INTO `permission_role` VALUES ('5', '1');
INSERT INTO `permission_role` VALUES ('5', '8');
INSERT INTO `permission_role` VALUES ('6', '1');
INSERT INTO `permission_role` VALUES ('7', '1');
INSERT INTO `permission_role` VALUES ('7', '2');
INSERT INTO `permission_role` VALUES ('8', '1');
INSERT INTO `permission_role` VALUES ('8', '2');
INSERT INTO `permission_role` VALUES ('9', '1');
INSERT INTO `permission_role` VALUES ('10', '1');
INSERT INTO `permission_role` VALUES ('11', '1');
INSERT INTO `permission_role` VALUES ('12', '1');
INSERT INTO `permission_role` VALUES ('12', '2');
INSERT INTO `permission_role` VALUES ('13', '1');
INSERT INTO `permission_role` VALUES ('13', '8');
INSERT INTO `permission_role` VALUES ('14', '1');
INSERT INTO `permission_role` VALUES ('14', '8');
INSERT INTO `permission_role` VALUES ('15', '1');
INSERT INTO `permission_role` VALUES ('15', '8');
INSERT INTO `permission_role` VALUES ('16', '1');
INSERT INTO `permission_role` VALUES ('17', '1');
INSERT INTO `permission_role` VALUES ('18', '1');
INSERT INTO `permission_role` VALUES ('19', '1');
INSERT INTO `permission_role` VALUES ('20', '1');
INSERT INTO `permission_role` VALUES ('21', '1');
INSERT INTO `permission_role` VALUES ('22', '1');
INSERT INTO `permission_role` VALUES ('23', '1');
INSERT INTO `permission_role` VALUES ('24', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', '管理整个系统的运行');
INSERT INTO `role` VALUES ('2', '文章审核员', '审核文章的发布');
INSERT INTO `role` VALUES ('3', '评论审查员', '审查劣质评论');
INSERT INTO `role` VALUES ('4', '读者', '普通用户注册');
INSERT INTO `role` VALUES ('5', '游客', '游客只有普通的阅读文章权限');
INSERT INTO `role` VALUES ('8', '博客测试角色', '12312321');
INSERT INTO `role` VALUES ('9', 'abc', 'abc');

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_foreign_key1` (`role_id`),
  CONSTRAINT `role_id_foreign_key1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id_foreign_key1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1', '1');
INSERT INTO `role_user` VALUES ('1', '2');
INSERT INTO `role_user` VALUES ('1', '3');
INSERT INTO `role_user` VALUES ('1', '4');
INSERT INTO `role_user` VALUES ('2', '4');
INSERT INTO `role_user` VALUES ('1', '5');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) NOT NULL,
  `tag_type` varchar(255) DEFAULT 'success',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'java', 'info');
INSERT INTO `tag` VALUES ('3', 'python', 'info');
INSERT INTO `tag` VALUES ('5', 'cpp', 'info');
INSERT INTO `tag` VALUES ('6', 'life', 'info');
INSERT INTO `tag` VALUES ('7', 'travel', 'info');
INSERT INTO `tag` VALUES ('8', 'emotion', 'info');
INSERT INTO `tag` VALUES ('9', '牛逼', 'info');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sign_in_ip` varchar(255) DEFAULT NULL,
  `last_sign_time` datetime DEFAULT NULL,
  `active_id` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`,`user_name`),
  UNIQUE KEY `userName_unique` (`user_name`) USING BTREE COMMENT 'userName 唯一不重复',
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wills', 'fc9ed5f8a39402fae2716c1509d05f27', '0.0.0.0', '2020-02-28 10:47:40', '123', '1');
INSERT INTO `user` VALUES ('2', 'tinx', 'fc9ed5f8a39402fae2716c1509d05f27', '0.0.0.0', '2020-05-31 22:34:34', '0000', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  KEY `user_id_foreign_key` (`user_id`),
  CONSTRAINT `user_id_foreign_key` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '0', '23', '17607113011', 'loveing490@qq.com', '山东省烟台市', '我会是那个最重要的人！！');
INSERT INTO `user_info` VALUES ('2', '0', '23', '18660547521', 'loveing490@qq.com', '山东省烟台市', '我会是那个最重要的人！！');

-- ----------------------------
-- Table structure for websettings
-- ----------------------------
DROP TABLE IF EXISTS `websettings`;
CREATE TABLE `websettings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `web_key` varchar(255) NOT NULL,
  `web_value` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of websettings
-- ----------------------------
INSERT INTO `websettings` VALUES ('1', 'qiniuAK', 'QXQpli1NK8PTIag3RGfVDzeH6TClXOB1TN9O5Bza', '七牛云账户AK');
INSERT INTO `websettings` VALUES ('2', 'qiniuSK', 'f2fpCbdmJ7NTFkbCxHlksnJu_K2Iq8SnMU-TQpyY', '七牛云账户SK');
INSERT INTO `websettings` VALUES ('3', 'qiniuBucket', 'wills-blog', '七牛云Bucket');
INSERT INTO `websettings` VALUES ('4', 'qiniuUploadUrl', 'http://up.qiniup.com', '七牛云上传Url');
INSERT INTO `websettings` VALUES ('5', 'imageUrl', 'http://image.tinx.top/', '外网访问地址');
