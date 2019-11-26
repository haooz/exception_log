PROFILE="produce"

APP_ADMIN_TAG="registry.znkf.kangmochou.com:1579/znkf/app_admin"
image_name="${APP_ADMIN_TAG}:19.11.02"

docker stop exception_log_${PROFILE}_2
docker rm -f exception_log_${PROFILE}_2

docker run -d \
-p 5102:8686 \
--name exception_log_${PROFILE}_2 \
-v /home/zkhc_zhh/exception_log/logs/${PROFILE}:/root/app/exception_log/${PROFILE} \
-v /home/zkhc_zhh/exception_log/target/exception_log.jar:/opt/app.jar \
-v /home/zkhc_zhh/exception_log/:/opt/exception_log \
-e spring.profiles.active=${PROFILE}  \
${image_name}


# 查看调试
docker exec -it exception_log_produce_2 /bin/sh

mkdir -p /etc/bash_completion.d/
ln -s /opt/spring-2.1.5.RELEASE/shell-completion/bash/spring /etc/bash_completion.d/spring
mkdir -p /usr/local/share/zsh/site-functions
ln -s /opt/spring-2.1.5.RELEASE/shell-completion/zsh/_spring /usr/local/share/zsh/site-functions/_spring
