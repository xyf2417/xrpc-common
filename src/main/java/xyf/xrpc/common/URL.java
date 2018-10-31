package xyf.xrpc.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * URL - Uniform Resource Locator 
 * @author xyf
 *
 */
public class URL implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4102868754391808736L;

	/**
	 * The protocol(e.g. zookeeper, redis, service, reference, etc.)
	 */
	private final String protocol;
	
	private final String host;
	
	private final int port;
	
	private final Map<String, String> parameters;
	
	private final String path;
	
	public URL() {
		protocol = null;
		host = null;
		port = 0;
		path = null;
		parameters = new HashMap<String, String>();
	}
	
	public URL(String protocol) {
		this(protocol, null, 0, null, null);
	}
	
	public URL(String protocol, String host) {
		this(protocol, host, 0, null, null);
	}
	
	public URL(String protocol, String host, int port) {
		this(protocol, host, port, null, null);
	}
	
	public URL(String protocol, String host, int port, String path) {
		this(protocol, host, port, path, null);
	}
	
	public URL(String protocol, String host, int port, String path,  Map<String, String> parameters) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.path = path;
		if(parameters == null) {
			parameters = new HashMap<String, String>();
		}
		this.parameters = Collections.unmodifiableMap(parameters);
	}

	public String getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}
	
	public String getParameter(String key) {
		if(key == null) {
			return null;
		}
		return parameters.get(key);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(protocol != null && protocol.length() > 0){
			builder.append(protocol + "://");
		}
		if(host != null && host.length() > 0) {
			builder.append(host + ":" + port + "/");
		}
		if(path != null && path.length() > 0) {
			builder.append(path);
		}
		if(parameters.size() > 0) {
			builder.append("?");
			for(Map.Entry<String, String> entry : parameters.entrySet()) {
				builder.append(entry.getKey() + "=" + entry.getValue() + "&");
			}
			builder.setLength(builder.length() - 1);//delete the last &
		}
		
		return builder.length() != 0 ? builder.toString() : ("URL(" + super.toString() + ")");
	}
	
}
