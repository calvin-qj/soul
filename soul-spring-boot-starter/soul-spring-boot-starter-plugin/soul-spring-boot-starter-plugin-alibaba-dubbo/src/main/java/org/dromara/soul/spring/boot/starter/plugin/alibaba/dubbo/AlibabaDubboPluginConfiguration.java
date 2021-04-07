/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.soul.spring.boot.starter.plugin.alibaba.dubbo;

import org.dromara.soul.plugin.alibaba.dubbo.AlibabaDubboPlugin;
import org.dromara.soul.plugin.alibaba.dubbo.handler.AlibabaDubboPluginDataHandler;
import org.dromara.soul.plugin.alibaba.dubbo.proxy.AlibabaDubboProxyService;
import org.dromara.soul.plugin.alibaba.dubbo.subscriber.AlibabaDubboMetaDataSubscriber;
import org.dromara.soul.plugin.api.SoulPlugin;
import org.dromara.soul.plugin.api.param.BodyParamResolveService;
import org.dromara.soul.plugin.base.handler.PluginDataHandler;
import org.dromara.soul.spring.boot.plugin.dubbo.common.DubboCommonConfiguration;
import org.dromara.soul.sync.data.api.MetaDataSubscriber;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Alibaba dubbo plugin configuration.
 *
 * @author xiaoyu(Myth)
 */
@Configuration
@ConditionalOnClass(AlibabaDubboPlugin.class)
@ImportAutoConfiguration(DubboCommonConfiguration.class)
public class AlibabaDubboPluginConfiguration {
    
    /**
     * Dubbo plugin soul plugin.
     *
     * @param dubboBodyParamResolveService the dubbo param resolve service
     * @return the soul plugin
     */
    @Bean
    public SoulPlugin alibabaDubboPlugin(final ObjectProvider<BodyParamResolveService> dubboBodyParamResolveService) {
        return new AlibabaDubboPlugin(new AlibabaDubboProxyService(dubboBodyParamResolveService.getIfAvailable()));
    }
    
    /**
     * Alibaba dubbo plugin data handler plugin data handler.
     *
     * @return the plugin data handler
     */
    @Bean
    public PluginDataHandler alibabaDubboPluginDataHandler() {
        return new AlibabaDubboPluginDataHandler();
    }
    
    /**
     * Dubbo meta data subscriber meta data subscriber.
     *
     * @return the meta data subscriber
     */
    @Bean
    public MetaDataSubscriber dubboMetaDataSubscriber() {
        return new AlibabaDubboMetaDataSubscriber();
    }
    
}
