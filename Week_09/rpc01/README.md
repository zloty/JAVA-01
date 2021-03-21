学习笔记
docker pull zookeeper 
docker run --privileged=true -d --name zookeeper -p 2181:2181  -d zookeeper
--privileged=true 使用该参数，container内的root拥有真正的root权限
