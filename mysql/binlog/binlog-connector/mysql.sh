docker run -itd --restart=always \
  --name my-mysql \
  -p 13306:3306 \
  -v ./config/mysqld.cnf:/etc/mysql/conf.d/mysqld.cnf \
  -e MYSQL_ROOT_PASSWORD=123456 mysql:5.7.8
