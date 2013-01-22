package kdx7214.necessities;

public class Vector3 implements Comparable<Vector3> {
	protected double x, y, z ;
	
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
	
    public Vector3(int x, int y, int z) {
    	this.x = (double) x ;
    	this.y = (double) y ;
    	this.z = (double) z ;
    }
	
    public Vector3(float x, float y, float z) {
    	this.x = (double) x ;
    	this.y = (double) y ;
    	this.z = (double) z ;
    }

    public Vector3(Vector3 v) {
    	this.x = v.x ;
    	this.y = v.y ;
    	this.z = v.z ;
    }
    
    public Vector3() {
    	this.x = this.y = this.z = 0.0 ;
    }
    
    public double getX() {
    	return x ;
    }
    
    public double getY() {
    	return y ;
    }
    
    public double getZ() {
    	return z ;
    }

    public void setX(double x) {
    	this.x = x ;
    }
    
    public void setY(double y) {
    	this.y = y ;
    }
    
    public void setZ(double z) {
    	this.z = z ;
    }
    
    public void setX(int x) {
    	this.x = (double) x ;
    }
    
    public void setY(int y) {
    	this.y = (double) y ;
    }

    public void setZ(int z) {
    	this.z = (double) z ;
    }
    
    public void setX(float x) {
    	this.x = (double) x ;
    }
    
    public void setY(float y) {
    	this.y = (double) y ;
    }
    
    public void setZ(float z) {
    	this.z = (double) z ;
    }

    public Vector3 add(Vector3 other) {
    	return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z) ;
    }

    public Vector3 add(Vector3... others) {
        double newX = x, newY = y, newZ = z ;
        for (int i = 0; i < others.length; ++i) {
            newX += others[i].x ;
            newY += others[i].y ;
            newZ += others[i].z ;
        }
        return new Vector3(newX, newY, newZ) ;
    }
    
    public Vector3 subtract(Vector3 other) {
    	return new Vector3(x - other.x, y - other.y, z - other.z) ; 
    }

    public Vector3 subtract(Vector3... others) {
        double newX = x, newY = y, newZ = z ;
        for (int i = 0; i < others.length; ++i) {
            newX -= others[i].x ;
            newY -= others[i].y ;
            newZ -= others[i].z ;
        }
        return new Vector3(newX, newY, newZ) ;
    }    
    
    public Vector3 multiply(Vector3 other) {
    	return new Vector3(x * other.x, y * other.y, z * other.z) ;
    }
    
    public Vector3 scale(double amount) {
    	return new Vector3(x * amount, y * amount, z * amount) ;
    }
    
    public Vector3 scale(float amount) {
    	return new Vector3(x * (double)amount, y * (double)amount, z * (double)amount) ;
    }

    public Vector3 scale(int amount) {
    	return new Vector3(x * (double)amount, y * (double)amount, z * (double)amount) ;
    }
    
    public double length() {
    	return Math.sqrt(x * x + y * y + z * z) ;
    }
    
    public double distance(Vector3 other) {
    	double tempx, tempy, tempz ;
    	tempx = other.x - x ;
    	tempy = other.y - y ;
    	tempz = other.z - z ;
    	return Math.sqrt(tempx * tempx + tempy * tempy + tempz * tempz) ;
    }
    
    public Vector3 normalize() {
    	return scale(1.0 / length()) ;
    }

    public double dot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z ;
    }
    
    public Vector3 cross(Vector3 other) {
        return new Vector3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
        ) ;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3)) {
            return false ;
        }

        Vector3 other = (Vector3) obj ;
        return (other.x == this.x) && (other.y == this.y) && (other.z == this.z) ;
    }

    @Override
    public int compareTo(Vector3 other) {
        if (y != other.y) return Double.compare(y, other.y) ;
        if (z != other.z) return Double.compare(z, other.z) ;
        if (x != other.x) return Double.compare(x, other.x) ;
        return 0;
    }


} // public class Vector3
