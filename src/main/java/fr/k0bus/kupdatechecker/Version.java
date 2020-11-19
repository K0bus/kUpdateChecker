package fr.k0bus.kupdatechecker;

public class Version {
    int major = 0;
    int minor = 0;
    int patch = 0;
    public Version(String version)
    {
        if(version.contains("-"))
            version = version.substring(0,version.indexOf('-'));
        System.out.println(version);
        String[] splitted = version.split("\\.", 3);
        if(splitted.length > 0)
            this.major = (splitted[0] != null) ? Integer.parseInt(splitted[0]) : 0;
        if(splitted.length > 1)
            this.minor = (splitted[1] != null) ? Integer.parseInt(splitted[1]) : 0;
        if(splitted.length > 2)
            this.patch = (splitted[2] != null) ? Integer.parseInt(splitted[2]) : 0;
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }

    public boolean biggerThan(Version v)
    {
        if(getMajor() > v.getMajor())
            return true;
        else if (getMajor() < v.getMajor())
            return false;
        if(getMinor() > v.getMinor())
            return true;
        else if(getMinor() < v.getMinor())
            return false;
        if(getPatch() > v.getPatch())
            return true;
        else if(getPatch() < v.getPatch())
            return false;
        return false;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }
}
