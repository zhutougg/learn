package com.example.demo.component;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @describe: TODO
 * @creat_user: hyson
 * @creat_date: 2019/5/5 14:09
 **/
@Component
public class EurekaChangeStateListen {
    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        String appName = eurekaInstanceCanceledEvent.getAppName();
        String id = eurekaInstanceCanceledEvent.getServerId();
        System.out.println(appName +" eurekaInstanceCanceledEvent "+id);

    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {
        String appName = eurekaInstanceRegisteredEvent.getInstanceInfo().getAppName();
        String ip = eurekaInstanceRegisteredEvent.getInstanceInfo().getIPAddr();
        System.out.println(appName +" eurekaInstanceRegisteredEvent "+ip);
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent eurekaInstanceRenewedEvent){
        String appName = eurekaInstanceRenewedEvent.getInstanceInfo().getAppName();
        String ip = eurekaInstanceRenewedEvent.getInstanceInfo().getIPAddr();
        System.out.println(appName +" eurekaInstanceRenewedEvent "+ip);
    }


}
