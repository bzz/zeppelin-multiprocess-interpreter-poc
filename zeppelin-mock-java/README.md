#Mock of Zeppelin (in Java)


## Install thrift 0.9 on os x
  
    #can not use homebrew as it is incompatibel 0.9.2
    #follow http://tachyon-project.org/Thrift.html for pre-requests (boost, libevent, etc)
    wget http://archive.apache.org/dist/thrift/0.9.0/thrift-0.9.0.tar.gz && tar xvzf thrift-0.9.0.tar.gz
    cd  cd thrift-0.9.0
    ./configure --prefix=/usr/local/ --with-boost=/usr/local --with-libevent=/usr/local \
                --without-ruby --disable-tests --without-php --without-perl \
                --without-hask --without-qt4 --without-cpp
    ## no cpp is crucial for build success: https://issues.apache.org/jira/browse/THRIFT-2266 https://issues.apache.org/jira/browse/THRIFT-2553
    make
    make install


## Generate Thrift Classes for RPC

    #make sure thrift 0.9 is installed
    thrift version

    #generate classes
    thrift -r --gen java -out src/gen/java src/main/thrift/interpreter.thrift