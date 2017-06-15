import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class ObtainCPU {

    public static String data() throws SigarException {
    	
    	String CPU = null;
    	Sigar sigar = new Sigar();
    	org.hyperic.sigar.CpuInfo[] cpuInfoList = sigar.getCpuInfoList();
    	
    			
    	for(org.hyperic.sigar.CpuInfo info : cpuInfoList){
    		CPU = info.getModel().toString();
    	}
    	
    	CPU.trim();
    	CPU = CPU.replaceAll("\\s","");
    	return CPU;
    	
    }

}