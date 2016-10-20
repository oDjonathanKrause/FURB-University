
public class Camera
{
	private float xMin;
	private float xMax;
	private float yMin;
	private float yMax;
	private float zMin;
	private float zMax;
	
	//Contrutor
	public Camera(float xMin, float xMax, float yMin, float yMax, float zMin, float zMax)
	{
		super();
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.zMin = zMin;
		this.zMax = zMax;
	}

	
	public void pan()
	{
		
	}
	
	public void zoomIn()
	{
		
	}
	
	public void zoomOut()
	{
		
	}
	
	
	
	
	
	
	
	
	//Gets e Sets
	public float getxMin()
	{
		return xMin;
	}

	public void setxMin(float xMin)
	{
		this.xMin = xMin;
	}

	public float getxMax()
	{
		return xMax;
	}

	public void setxMax(float xMax)
	{
		this.xMax = xMax;
	}

	public float getyMin()
	{
		return yMin;
	}

	public void setyMin(float yMin)
	{
		this.yMin = yMin;
	}

	public float getyMax()
	{
		return yMax;
	}

	public void setyMax(float yMax)
	{
		this.yMax = yMax;
	}

	public float getzMin()
	{
		return zMin;
	}

	public void setzMin(float zMin)
	{
		this.zMin = zMin;
	}

	public float getzMax()
	{
		return zMax;
	}

	public void setzMax(float zMax)
	{
		this.zMax = zMax;
	}
	
	
	
	
}
