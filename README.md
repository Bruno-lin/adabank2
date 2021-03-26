## Note

1. 用户登陆时，从前端传过来的密码不是明文，而是加密的密文，须使用`SessionDatabase::decryptCaesar`进行解密。过往版本使用`SessionDatabase::decryptReverse`进行解密，现已废弃。
2. 本项目依赖于一些第三方库，一般会通过maven自动下载。如果下载速度过慢，可以把maven的镜像换成国内，如[阿里云](https://maven.aliyun.com/mvn/guide)。

## Quick Start

有两种方式运行该项目：

1. 在IDEA里运行`BankApp`
2. 在命令行执行`mvn springboot:run`

成功运行后，访问 http://localhost:8080 即可。