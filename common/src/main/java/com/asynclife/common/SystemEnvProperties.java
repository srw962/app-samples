package com.asynclife.common;

import java.util.Map;
import java.util.Properties;

public class SystemEnvProperties {

	/*
USERPROFILE	C:\Users\Administrator
ProgramData	C:\ProgramData
PATHEXT	.COM;.EXE;.BAT;.CMD;.VBS;.VBE;.JS;.JSE;.WSF;.WSH;.MSC
windows_tracing_logfile	C:\BVTBin\Tests\installpackage\csilogfile.log
JAVA_HOME	C:\Java\jdk1.7.0_40
MAVEN_HOME	F:\asynclife\local\apache-maven-3.2.3
ProgramFiles(x86)	C:\Program Files (x86)
windows_tracing_flags	3
TEMP	C:\Users\ADMINI~1\AppData\Local\Temp
SystemDrive	C:
MOZ_PLUGIN_PATH	d:\Program Files (x86)\Foxit Software\Foxit Reader\plugins\
NEXUS_HOME	F:\asynclife\local\nexus-latest-bundle\nexus-2.9.0-04
ProgramFiles	C:\Program Files
Path	C:/Java/jdk1.7.0_40/bin/../jre/bin/server;C:/Java/jdk1.7.0_40/bin/../jre/bin;C:/Java/jdk1.7.0_40/bin/../jre/lib/amd64;C:\Java\jdk1.7.0_40\bin;F:\asynclife\local\apache-maven-3.2.3\bin;F:\asynclife\local\nexus-latest-bundle\nexus-2.9.0-04\bin;C:\Program Files (x86)\AMD APP\bin\x86_64;C:\Program Files (x86)\AMD APP\bin\x86;C:\Program Files\Broadcom\Broadcom 802.11 Network Adapter\Driver;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files\Intel\WiFi\bin\;C:\Program Files\Common Files\Intel\WirelessCommon\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;D:\install\mysql\bin;F:\asynclife\soft\eclipse-jee-luna-SR2-win32-x86_64\eclipse;
HOMEDRIVE	C:
PROCESSOR_REVISION	2a07
USERDOMAIN	hqh-PC
ALLUSERSPROFILE	C:\ProgramData
AMDAPPSDKROOT	C:\Program Files (x86)\AMD APP\
ProgramW6432	C:\Program Files
PROCESSOR_IDENTIFIER	Intel64 Family 6 Model 42 Stepping 7, GenuineIntel
SESSIONNAME	Console
TMP	C:\Users\ADMINI~1\AppData\Local\Temp
CommonProgramFiles	C:\Program Files\Common Files
=::	::\
LOGONSERVER	\\HQH-PC
PROCESSOR_ARCHITECTURE	AMD64
FP_NO_HOST_CHECK	NO
OS	Windows_NT
HOMEPATH	\Users\Administrator
PROCESSOR_LEVEL	6
CommonProgramW6432	C:\Program Files\Common Files
LOCALAPPDATA	C:\Users\Administrator\AppData\Local
COMPUTERNAME	HQH-PC
windir	C:\Windows
SystemRoot	C:\Windows
NUMBER_OF_PROCESSORS	4
USERNAME	Administrator
PUBLIC	C:\Users\Public
PSModulePath	C:\Windows\system32\WindowsPowerShell\v1.0\Modules\
CommonProgramFiles(x86)	C:\Program Files (x86)\Common Files
ComSpec	C:\Windows\system32\cmd.exe
APPDATA	C:\Users\Administrator\AppData\Roaming
	 */
	public static Map<String,String> getSystemProperties() {
		
		Map<String,String> envMap = System.getenv();
		for(Map.Entry<String, String> entry : envMap.entrySet()) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		
		return null;
	}
	
	/*
java.runtime.name->Java(TM) SE Runtime Environment
sun.boot.library.path->C:\Java\jdk1.7.0_40\jre\bin
java.vm.version->24.0-b56
java.vm.vendor->Oracle Corporation
java.vendor.url->http://java.oracle.com/
path.separator->;
java.vm.name->Java HotSpot(TM) 64-Bit Server VM
file.encoding.pkg->sun.io
user.country->CN
user.script->
sun.java.launcher->SUN_STANDARD
sun.os.patch.level->Service Pack 1
java.vm.specification.name->Java Virtual Machine Specification
user.dir->F:\asynclife\source\app\common
java.runtime.version->1.7.0_40-b43
java.awt.graphicsenv->sun.awt.Win32GraphicsEnvironment
java.endorsed.dirs->C:\Java\jdk1.7.0_40\jre\lib\endorsed
os.arch->amd64
java.io.tmpdir->C:\Users\ADMINI~1\AppData\Local\Temp\
line.separator->

java.vm.specification.vendor->Oracle Corporation
user.variant->
os.name->Windows 7
sun.jnu.encoding->GBK
java.library.path->C:\Java\jdk1.7.0_40\bin;C:\Windows\Sun\Java\bin;C:\Windows\system32;C:\Windows;C:/Java/jdk1.7.0_40/bin/../jre/bin/server;C:/Java/jdk1.7.0_40/bin/../jre/bin;C:/Java/jdk1.7.0_40/bin/../jre/lib/amd64;C:\Java\jdk1.7.0_40\bin;F:\asynclife\local\apache-maven-3.2.3\bin;F:\asynclife\local\nexus-latest-bundle\nexus-2.9.0-04\bin;C:\Program Files (x86)\AMD APP\bin\x86_64;C:\Program Files (x86)\AMD APP\bin\x86;C:\Program Files\Broadcom\Broadcom 802.11 Network Adapter\Driver;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Program Files\Intel\WiFi\bin\;C:\Program Files\Common Files\Intel\WirelessCommon\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;D:\install\mysql\bin;F:\asynclife\soft\eclipse-jee-luna-SR2-win32-x86_64\eclipse;;.
java.specification.name->Java Platform API Specification
java.class.version->51.0
sun.management.compiler->HotSpot 64-Bit Tiered Compilers
os.version->6.1
user.home->C:\Users\Administrator
user.timezone->
java.awt.printerjob->sun.awt.windows.WPrinterJob
file.encoding->UTF-8
java.specification.version->1.7
java.class.path->F:\asynclife\source\app\common\target\classes;E:\repository\maven3\repos\com\google\code\gson\gson\2.3.1\gson-2.3.1.jar
user.name->Administrator
java.vm.specification.version->1.7
sun.java.command->com.asynclife.common.CommonUtil
java.home->C:\Java\jdk1.7.0_40\jre
sun.arch.data.model->64
user.language->zh
java.specification.vendor->Oracle Corporation
awt.toolkit->sun.awt.windows.WToolkit
java.vm.info->mixed mode
java.version->1.7.0_40
java.ext.dirs->C:\Java\jdk1.7.0_40\jre\lib\ext;C:\Windows\Sun\Java\lib\ext
sun.boot.class.path->C:\Java\jdk1.7.0_40\jre\lib\resources.jar;C:\Java\jdk1.7.0_40\jre\lib\rt.jar;C:\Java\jdk1.7.0_40\jre\lib\sunrsasign.jar;C:\Java\jdk1.7.0_40\jre\lib\jsse.jar;C:\Java\jdk1.7.0_40\jre\lib\jce.jar;C:\Java\jdk1.7.0_40\jre\lib\charsets.jar;C:\Java\jdk1.7.0_40\jre\lib\jfr.jar;C:\Java\jdk1.7.0_40\jre\classes
java.vendor->Oracle Corporation
file.separator->\
java.vendor.url.bug->http://bugreport.sun.com/bugreport/
sun.io.unicode.encoding->UnicodeLittle
sun.cpu.endian->little
sun.desktop->windows
sun.cpu.isalist->amd64
	 */
	public static Map<String,String> getProperties() {
		Properties props = System.getProperties();
		
		for(Object prop : props.keySet()) {
			System.out.println(prop.toString()+"->"+props.getProperty(prop.toString()));
		}
		
		return null;
	}
}
