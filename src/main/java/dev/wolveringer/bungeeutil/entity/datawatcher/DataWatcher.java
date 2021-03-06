package dev.wolveringer.bungeeutil.entity.datawatcher;

import dev.wolveringer.bungeeutil.entity.datawatcher.impl.v1_10_DataWatcher;
import dev.wolveringer.bungeeutil.entity.datawatcher.impl.v1_11_DataWatcher;
import dev.wolveringer.bungeeutil.entity.datawatcher.impl.v1_12_DataWatcher;
import dev.wolveringer.bungeeutil.entity.datawatcher.impl.v1_08_DataWatcher;
import dev.wolveringer.bungeeutil.entity.datawatcher.impl.v1_09_DataWatcher;
import dev.wolveringer.bungeeutil.packetlib.reader.PacketDataSerializer;
import dev.wolveringer.bungeeutil.player.ClientVersion.BigClientVersion;

public interface DataWatcher {

	public static DataWatcher createDataWatcher(BigClientVersion version){
		switch (version) {
			case v1_8:
				return new v1_08_DataWatcher();
			case v1_9:
				return new v1_09_DataWatcher();
			case v1_10:
				return new v1_10_DataWatcher();
			case v1_11:
				return new v1_11_DataWatcher();
			case v1_12:
				return new v1_12_DataWatcher();
			default:
				throw new RuntimeException("Cant find datawatcher for "+version);
		}
	}

	public static DataWatcher createDataWatcher(BigClientVersion version,PacketDataSerializer watcher){
		DataWatcher res = createDataWatcher(version);
		res.read(watcher);
		return res;
	}

	public abstract DataWatcher copy();

	
	public abstract Object get(int i);

	public abstract <T> T get(Class<T> clazz, int i);
	
	public abstract byte getByte(int i);

	public abstract float getFloat(int i);

	public abstract int getInt(int i);

	public abstract short getShort(int i);
	
	public abstract String getString(int i);

	public abstract void setValue(int pos, Object object);

	public abstract EntityDataWatcher getEntityDataWatcher();
	
	public abstract <T extends EntityDataWatcher> T getSpecialDataWatcher(Class<T> clazz);

	@Override
	public abstract String toString();

	public abstract void write(PacketDataSerializer packetdataserializer);
	public abstract void read(PacketDataSerializer packetdataserializer);
}
