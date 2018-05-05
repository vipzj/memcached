package com.example.demo;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class RunService {

    @Autowired
    private XMemcachedClientBuilder xMemcachedClientBuilder;



    @PostConstruct
    public void init() throws IOException {

        MemcachedClient client = xMemcachedClientBuilder.build();

        int c = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(c);

        List<Callable<String>> tasks = new ArrayList<Callable<String>>();

        for(int i = 0 ;i< c;i++){
            tasks.add(new Callable<String>() {
                @Override
                public String call() throws Exception {

                    client.get("");
                    return "";
                }
            });
        }


        System.out.println(xMemcachedClientBuilder);
    }

}
