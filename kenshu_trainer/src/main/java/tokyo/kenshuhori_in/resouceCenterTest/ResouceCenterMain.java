package tokyo.kenshuhori_in.resouceCenterTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tokyo.kenshuhori_in.SubMainInterface;

public class ResouceCenterMain implements SubMainInterface {

	public static void main(String[] args) {
		new ResouceCenterMain().execute();
	}

	List<VersionDto> VERSIONSINDB;
	List<VersionDto> REMOTELIST;
	public static final String CIU_PRODUCT_REGEX = "(CIU)(\\d{5})";

	@Override
	public void execute() {
		setResourceCenterMainData();
		VersionDto result = getLatestCiuVersionForAdmin();
		System.out.println("result : " + result.getVersion());
	}

	public void setResourceCenterMainData() {
		VERSIONSINDB = new ArrayList<VersionDto>();
		VERSIONSINDB.add(new VersionDto("CIU12000"));
        VERSIONSINDB.add(new VersionDto("CIU12100"));
        VERSIONSINDB.add(new VersionDto("CIU12200"));
        VERSIONSINDB.add(new VersionDto("CIU12300"));

        REMOTELIST = new ArrayList<VersionDto>();
        REMOTELIST.add(new VersionDto("CIU12000"));
        REMOTELIST.add(new VersionDto("CIU12100"));
        REMOTELIST.add(new VersionDto("CIU12200"));
        REMOTELIST.add(new VersionDto("CIU12300"));
	}

	public VersionDto getLatestCiuVersionForAdmin(){

        Comparator<VersionDto> cmp = (i, j) -> j.getVersion().compareTo( i.getVersion() );

        VersionDto self = new VersionDto("CIU12200");

        List<VersionDto> versionsInDb = VERSIONSINDB;

        File[] localCiu = new File(".").listFiles(((dir, name) -> name.matches(CIU_PRODUCT_REGEX)));

        List<String> updatePackage = Stream.of(localCiu).map( f -> f.getName() ).collect(Collectors.toList());

        VersionDto local = versionsInDb.stream()
                .filter(dto -> updatePackage.contains(dto.getVersion()))
                .sorted( cmp  )
                .findFirst()
                .orElse(self);

        List<VersionDto> remoteCiuList = REMOTELIST;

        VersionDto latestInRemote = remoteCiuList.stream()
        		.sorted( cmp  )
        		.findFirst()
        		.get();

        return remoteCiuList.stream()
                .filter( remote -> cmp.compare(remote, local) < 0 )
                .sorted( cmp  )
                .findFirst()
                .orElse(local);
    }
}
