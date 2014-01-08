package com.udpwork.ssdb;

/**
 * SSDB Java client SDK.
 * Example:
 * <pre>
 * SSDB ssdb = new SSDB("127.0.0.1", 8888);
 * ssdb.set("a", "123");
 * byte[] val = ssdb.get("a");
 * </pre>
 */
public class SSDB{
	public Link link;

	public SSDB(String host, int port) throws Exception{
		this(host, port, 0);
	}

	public SSDB(String host, int port, int timeout_ms) throws Exception{
		link = new Link(host, port, timeout_ms);
	}

    public SSDB() {
    }

    public void close(){
		link.close();
	}
	
	/* kv */

	public void set(byte[] key, byte[] val) throws Exception{
		Response resp = link.request("set", key, val);
		if(resp.ok()){
			return;
		}
		resp.exception();
	}
	
	public void set(String key, byte[] val) throws Exception{
		set(key.getBytes(), val);
	}
	
	public void set(String key, String val) throws Exception{
		set(key, val.getBytes());
	}

	public void del(byte[] key) throws Exception{
		Response resp = link.request("del", key);
		if(resp.ok()){
			return;
		}
		resp.exception();
	}
	
	public void del(String key) throws Exception{
		del(key.getBytes());
	}

	/***
	 * 
	 * @param key
	 * @return null if not found
	 * @throws Exception
	 */
	public byte[] get(byte[] key) throws Exception{
		Response resp = link.request("get", key);
		if(resp.not_found()){
			return null;
		}
		if(resp.raw.size() != 2){
			throw new Exception("Invalid response");
		}
		if(resp.ok()){
			return resp.raw.get(1);
		}
		resp.exception();
		return null;
	}
	
	/***
	 * 
	 * @param key
	 * @return null if not found
	 * @throws Exception
	 */
	public byte[] get(String key) throws Exception{
		return get(key.getBytes());
	}

	private Response _scan(String cmd, String key_start, String key_end, int limit) throws Exception{
		if(key_start == null){
			key_start = "";
		}
		if(key_end == null){
			key_end = "";
		}
		Response resp = link.request(cmd, key_start, key_end, (new Integer(limit)).toString());
		if(!resp.ok()){
			resp.exception();
		}
		resp.buildMap();
		return resp;
	}

    /**
     * List key-value pairs with keys in range (key_start, key_end].("", ""] means no range limit.
     * @param key_start  The lower bound(not included) of keys to be returned, empty string means -inf(no limit).
     * @param key_end The upper bound(inclusive) of keys to be returned, empty string means +inf(no limit).
     * @param limit Up to that many pairs will be returned.
     * @return
     * @throws Exception
     */
	public Response scan(String key_start, String key_end, int limit) throws Exception{
		return _scan("scan", key_start, key_end, limit);
	}

    /**
     * List key-value pairs with keys in range (key_start, key_end], in reverse order.
     * @param key_start The upper bound(not included) of keys to be returned, empty string means +inf(no limit).
     * @param key_end The lower bound(inclusive) of keys to be returned, empty string means -inf(no limit).
     * @param limit   Up to that many pairs will be returned.
     * @return
     * @throws Exception
     */
	public Response rscan(String key_start, String key_end, int limit) throws Exception{
		return _scan("rscan", key_start, key_end, limit);
	}

    /**
     * Increment the number stored at key by num. The num argument could be a negative integer. <br/>
     * The old number is first converted to an integer before increment, assuming it was stored as literal integer.
     * @param key
     * @param by
     * @return
     * @throws Exception
     */
	public long incr(String key, long by) throws Exception{
		Response resp = link.request("incr", key, (new Long(by)).toString());
		if(!resp.ok()){
			resp.exception();
		}
		if(resp.raw.size() != 2){
			throw new Exception("Invalid response");
		}
		long ret = 0;
		ret = Long.parseLong(new String(resp.raw.get(1)));
		return ret;
	}
	
	/* hashmap */

	public void hset(String name, byte[] key, byte[] val) throws Exception{
		Response resp = link.request("hset", name.getBytes(), key, val);
		if(resp.ok()){
			return;
		}
		resp.exception();
	}
	
	public void hset(String name, String key, byte[] val) throws Exception{
		this.hset(name, key.getBytes(), val);
	}

    /**
     * Set the string value in argument as value of the key of a hashmap.
     * @param name The name of the hashmap
     * @param key The key of the key-value pair in the hashmap
     * @param val The value of the key-value pair in the hashmap
     * @throws Exception
     */
	public void hset(String name, String key, String val) throws Exception{
		this.hset(name, key, val.getBytes());
	}
	
	public void hdel(String name, byte[] key) throws Exception{
		Response resp = link.request("hdel", name.getBytes(), key);
		if(resp.ok()){
			return;
		}
		resp.exception();
	}
	
	public void hdel(String name, String key) throws Exception{
		this.hdel(name, key.getBytes());
	}

	/**
	 * Get the value related to the specified key of a hashmap
	 * @param name The name of the hashmap
	 * @param key The key of the key-value pair in the hashmap
	 * @return null if not found
	 * @throws Exception
	 */
	public byte[] hget(String name, byte[] key) throws Exception{
		Response resp = link.request("hget", name.getBytes(), key);
		if(resp.not_found()){
			return null;
		}
		if(resp.raw.size() != 2){
			throw new Exception("Invalid response");
		}
		if(resp.ok()){
			return resp.raw.get(1);
		}
		resp.exception();
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @param key
	 * @return null if not found
	 * @throws Exception
	 */
	public byte[] hget(String name, String key) throws Exception{
		return hget(name, key.getBytes());
	}

	private Response _hscan(String cmd, String name, String key_start, String key_end, int limit) throws Exception{
		if(key_start == null){
			key_start = "";
		}
		if(key_end == null){
			key_end = "";
		}
		Response resp = link.request(cmd, name, key_start, key_end, (new Integer(limit)).toString());
		if(!resp.ok()){
			resp.exception();
		}
		for(int i=1; i<resp.raw.size(); i+=2){
			byte[] k = resp.raw.get(i);
			byte[] v = resp.raw.get(i+1);
			resp.keys.add(k);
			resp.items.put(k, v);
		}
		return resp;
	}

    /**
     * List key-value pairs of a hashmap with keys in range (key_start, key_end].("", ""] means no range limit.
     * @param name The name of the hashmap
     * @param key_start The lower bound(not included) of keys to be returned, empty string means -inf(no limit).
     * @param key_end The upper bound(inclusive) of keys to be returned, empty string means +inf(no limit).
     * @param limit  Up to that many pairs will be returned.
     * @return
     * @throws Exception
     */
	public Response hscan(String name, String key_start, String key_end, int limit) throws Exception{
		return this._hscan("hscan", name, key_start, key_end, limit);
	}
	
	public Response hrscan(String name, String key_start, String key_end, int limit) throws Exception{
		return this._hscan("hrscan", name, key_start, key_end, limit);
	}
	
	public long hincr(String name, String key, long by) throws Exception{
		Response resp = link.request("hincr", name, key, (new Long(by)).toString());
		if(!resp.ok()){
			resp.exception();
		}
		if(resp.raw.size() != 2){
			throw new Exception("Invalid response");
		}
		long ret = 0;
		ret = Long.parseLong(new String(resp.raw.get(1)));
		return ret;
	}
	
	/* zset */

	public void zset(String name, byte[] key, double score) throws Exception{
		Response resp = link.request("zset", name.getBytes(), key, (new Double(score)).toString().getBytes());
		if(resp.ok()){
			return;
		}
		resp.exception();
	}

    /**
     * Set the score of the key of a zset.
     * @param name The name of the zset
     * @param key The key of the key-score pair in the hashmap
     * @param score   The score of the key-score pair in the hashmap
     * @throws Exception
     */
	public void zset(String name, String key, double score) throws Exception{
		zset(name, key.getBytes(), score);
	}
	
	public void zdel(String name, byte[] key) throws Exception{
		Response resp = link.request("zdel", name.getBytes(), key);
		if(resp.ok()){
			return;
		}
		resp.exception();
	}
	
	public void zdel(String name, String key) throws Exception{
		this.zdel(name, key.getBytes());
	}
	
	/**
	 * 
	 * @param name
	 * @param key
	 * @return Double.NaN if not found.
	 * @throws Exception
	 */
	public double zget(String name, byte[] key) throws Exception{
		Response resp = link.request("zget", name.getBytes(), key);
		if(resp.not_found()){
			return Double.NaN;
		}
		if(resp.raw.size() != 2){
			throw new Exception("Invalid response");
		}
		if(resp.ok()){
			return Double.parseDouble(new String(resp.raw.get(1)));
		}
		resp.exception();
		return 0;
	}

	/**
	 * 
	 * @param name
	 * @param key
	 * @return Double.NaN if not found.
	 * @throws Exception
	 */
	public double zget(String name, String key) throws Exception{
		return zget(name, key.getBytes());
	}

	private Response _zscan(String cmd, String name, String key, Double score_start, Double score_end, int limit) throws Exception{
		if(key == null){
			key = "";
		}
		String ss ="";
		if(score_start != null){
			ss = score_start.toString();
		}
		String se ="";
		if(score_start != null){
			se = score_end.toString();
		}
		Response resp = link.request(cmd, name, key, ss, se, (new Integer(limit)).toString());
		if(!resp.ok()){
			resp.exception();
		}
		for(int i=1; i<resp.raw.size(); i+=2){
			byte[] k = resp.raw.get(i);
			byte[] v = resp.raw.get(i+1);
			resp.keys.add(k);
			resp.items.put(k, v);
		}
		return resp;
	}

    /**
     * List key-score pairs of a zset. See method.
      * @param name The name of the zset
     * @param key See method zkeys().
     * @param score_start  See method zkeys().
     * @param score_end See method zkeys().
     * @param limit Up to that many pairs will be returned.
     * @return
     * @throws Exception
     */
	public Response zscan(String name, String key, Double score_start, Double score_end, int limit) throws Exception{
		return this._zscan("zscan", name, key, score_start, score_end, limit);
	}
	
	public Response zrscan(String name, String key, Double score_start, Double score_end, int limit) throws Exception{
		return this._zscan("zrscan", name, key, score_start, score_end, limit);
	}
	
	public long zincr(String name, String key, long by) throws Exception{
		Response resp = link.request("zincr", name, key, (new Long(by)).toString());
		if(!resp.ok()){
			resp.exception();
		}
		if(resp.raw.size() != 2){
			throw new Exception("Invalid response");
		}
		long ret = 0;
		ret = Long.parseLong(new String(resp.raw.get(1)));
		return ret;
	}

	/****************/
	
	public Response multi_get(String...keys) throws Exception{
		Response resp = link.request("multi_get", keys);
		if(!resp.ok()){
			resp.exception();
		}
		resp.buildMap();
		return resp;
	}
	
	public Response multi_get(byte[]...keys) throws Exception{
		Response resp = link.request("multi_get", keys);
		if(!resp.ok()){
			resp.exception();
		}
		resp.buildMap();
		return resp;
	}

    /**
     * Set multiple key-value pairs(kvs) in one method call.
     * @param kvs
     * @throws Exception
     */
	public void multi_set(String...kvs) throws Exception{
		if(kvs.length % 2 != 0){
			throw new Exception("Invalid arguments count");
		}
		Response resp = link.request("multi_set", kvs);
		if(!resp.ok()){
			resp.exception();
		}
	}
	
	public void multi_set(byte[]...kvs) throws Exception{
		if(kvs.length % 2 != 0){
			throw new Exception("Invalid arguments count");
		}
		Response resp = link.request("multi_set", kvs);
		if(!resp.ok()){
			resp.exception();
		}
	}
	
	public Response multi_del(String...keys) throws Exception{
		Response resp = link.request("multi_del", keys);
		if(!resp.ok()){
			resp.exception();
		}
		resp.buildMap();
		return resp;
	}
	
	public Response multi_del(byte[]...keys) throws Exception{
		Response resp = link.request("multi_del", keys);
		if(!resp.ok()){
			resp.exception();
		}
		resp.buildMap();
		return resp;
	}
}
