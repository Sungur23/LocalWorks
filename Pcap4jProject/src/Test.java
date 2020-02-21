import java.net.Inet4Address;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapAddress;
import org.pcap4j.core.PcapDumper;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

public class Test {

	public static int count = 0;
	public static Predicate<PcapNetworkInterface> isNotLoopBack = (dev) -> !dev.isLoopBack();

	public static BiFunction<PcapNetworkInterface, String, Boolean> ipControl = (dev, ip) -> {
		for (PcapAddress pAd : dev.getAddresses()) {
			if (pAd.getAddress() instanceof Inet4Address) {
				return pAd.getAddress().getHostAddress().equalsIgnoreCase(ip);
			}
		}
		return false;
	};

	public static BiPredicate<PcapNetworkInterface, String> ipFilter = (dev, ip) -> ipControl.apply(dev, ip);

	public static class PaketDinleyici implements PacketListener {
		PcapDumper dumper;

		public PaketDinleyici(PcapDumper dumper) {
			this.dumper = dumper;
		}

		@Override
		public void gotPacket(Packet packet) {

			// Write packets as needed
			try {
				dumper.dump(packet);
			} catch (NotOpenException e) {
				e.printStackTrace();
			}
		}

		public void close() {
			if (dumper != null)
				dumper.close();
		}
	}

	public static void main(String[] args) throws Exception {

		// kayitIslemleri();
		oku();

		System.err.println("Finish");
	}

	public static void kayitIslemleri() throws Exception {

		PcapNetworkInterface device = getCurrentDevice();
		System.out.println("You chose: " + device);

		// Open the device and get a handle
		int snapshotLength = 65536; // in bytes
		int readTimeout = 50; // in milliseconds
		final PcapHandle handle;
		handle = device.openLive(snapshotLength, PromiscuousMode.PROMISCUOUS, readTimeout);

		// Set a filter to only listen for tcp packets on port 80 (HTTP)
		String filter = "";
		handle.setFilter(filter, BpfCompileMode.OPTIMIZE);

		dinleVeYaz(handle);
		dinleVeYaz(handle);
		dinleVeYaz(handle);
		dinleVeYaz(handle);

		// Cleanup when complete
		handle.close();
	}

	public static PcapNetworkInterface getCurrentDevice() throws Exception {
		PcapNetworkInterface selected = null;
		List<PcapNetworkInterface> allDevs = null;

		allDevs = Pcaps.findAllDevs();
		String ip = Inet4Address.getLocalHost().getHostAddress();
		List<PcapNetworkInterface> filteredList = allDevs.stream().filter(isNotLoopBack)
				.filter(dev -> ipFilter.test(dev, ip)).filter(Objects::nonNull).collect(Collectors.toList());
		if (filteredList != null && filteredList.size() == 1) {
			selected = filteredList.get(0);
		}

		return selected;
	}

	public static void dinleVeYaz(PcapHandle handle) throws Exception {

		PcapDumper dumper = handle.dumpOpen("dump" + count + ".pcap");
		PaketDinleyici listener = new PaketDinleyici(dumper);

		int maxPackets = 30;
		handle.loop(maxPackets, listener);

		dumper.close();
		count++;
	}

	public static void oku() throws Exception {

		PcapHandle handle = Pcaps.openOffline("dump0.pcap");
		Packet p = handle.getNextPacket();
		System.err.println(new Date(handle.getTimestamp().getTime()));
		p = handle.getNextPacket();
		System.err.println(new Date(handle.getTimestamp().getTime()));
		p = handle.getNextPacket();
		System.err.println(new Date(handle.getTimestamp().getTime()));
		p = handle.getNextPacket();
		System.err.println(new Date(handle.getTimestamp().getTime()));
		p = handle.getNextPacket();
		System.err.println(new Date(handle.getTimestamp().getTime()));
	}

}
