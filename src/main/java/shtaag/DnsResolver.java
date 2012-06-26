/**
 * 
 */
package shtaag;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author takei_s
 * @date 2012/06/25
 */
public class DnsResolver {
	
	Map<String, InetAddress> dnsCache = new HashMap<String, InetAddress>();
	
	public InetAddress resolve(String domain) throws UnknownHostException {
		
		if (dnsCache.containsKey(domain)) {
			return dnsCache.get(domain);
		} else {
			InetAddress address = InetAddress.getByName(domain);
			dnsCache.put(domain, address);
			return address;
		}
	}

}
