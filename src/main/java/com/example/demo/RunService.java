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
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RunService {

    @Autowired
    private XMemcachedClientBuilder xMemcachedClientBuilder;

    private AtomicInteger counts = new AtomicInteger();


    @PostConstruct
    public void init() throws Exception {

        MemcachedClient client = xMemcachedClientBuilder.build();

        int c = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(c);

        List<Callable<String>> tasks = new ArrayList<Callable<String>>();

        for(int i = 0 ;i< c;i++){
            tasks.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    client.set("counts",36000,counts.addAndGet(1));
                    return "";
                }
            });
        }

        executorService.invokeAny(tasks);

        System.out.println(counts.get());
    }

}
