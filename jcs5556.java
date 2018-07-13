package com.example.demo;

import static org.assertj.core.api.Assertions.catchThrowable;

import java.io.IOException;
import java.rmi.ConnectException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.jcs.access.exception.CacheException;
import org.apache.commons.jcs.admin.CacheRegionInfo;
import org.apache.commons.jcs.engine.CacheStatus;
import org.apache.commons.jcs.engine.control.CompositeCache;
import org.apache.commons.jcs.engine.control.CompositeCacheManager;
import org.apache.commons.jcs.engine.stats.behavior.IStatElement;
import org.apache.commons.jcs.engine.stats.behavior.IStats;

import com.jayway.jsonpath.spi.cache.Cache;

public class jcs5556 {
	
	public static void main(String[] args) throws IOException, InterruptedException, ConnectException {

		 		CompositeCacheManager ccm1 = CompositeCacheManager.getInstance();
		 		boolean cachenotfound = false;
		 		List<IStats> StatsList = ccm1.getCache("test").getStatistics().getAuxiliaryCacheStats();
		 		for(IStats s : StatsList) {
		 			List<IStatElement<?>> ElementList = s.getStatElements();
		 			for(IStatElement<?> elem : ElementList) {
		 				if (elem.toString().equals("Status = ERROR")) {
		 					cachenotfound = true;
		 					ccm1.shutDown();
		 					
		 					break;
		 				}
		 				
		 					
		 			}
		 		}
		 		if(cachenotfound) {
		 			//JCS.shutdown();
		 			Properties props = new Properties();
					
					props.put("jcs.region.test","DC,RFailover");
				
					
					props.put("jcs.region.test.cacheattributes","org.apache.commons.jcs.engine.CompositeCacheAttributes");
					props.put("jcs.region.test.cacheattributes.MemoryCacheName","org.apache.commons.jcs.engine.memory.lru.LRUMemoryCache");
					props.put("jcs.region.test.cacheattributes.MaxObjects","1000");
					
					props.put("jcs.region.test.elementattributes.IsSpool","false");
					props.put("jcs.region.test.elementattributes","org.apache.commons.jcs.engine.ElementAttributes");
					props.put("jcs.auxiliary.RFailover","org.apache.commons.jcs.auxiliary.remote.RemoteCacheFactory");
					props.put("jcs.auxiliary.RFailover.attributes","org.apache.commons.jcs.auxiliary.remote.RemoteCacheAttributes");
					props.put("jcs.auxiliary.RFailover.attributes.RemoveUponRemotePut","true");
					props.put("jcs.auxiliary.RFailover.attributes.GetOnly","false");
					props.put("jcs.auxiliary.RFailover.attributes.FailoverServers","127.0.0.1:5556");
					   
					
				 	CompositeCacheManager ccm = CompositeCacheManager.getUnconfiguredInstance();
				 	ccm.configure(props, true, true);
					System.out.println(ccm.getStats());
					CacheAccess<String, String> cache= JCS.getInstance("test");
					System.out.println(cache.get("spring"));
		 		}
			 	CacheAccess<String, String> cache= JCS.getInstance("test");		
	
	          //cache.put("spring", "cloud");
	           //System.out.println(cache.getStats());
	            //cache.remove(cache_key);
	            System.out.println(cache.get("spring"));
	            //System.out.println(cache.getStats());
	           /*if (cache != null)
	        	   JCS.shutdown();*/

			
	
	}

}
