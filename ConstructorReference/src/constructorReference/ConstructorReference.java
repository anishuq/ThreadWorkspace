package constructorReference;

class Employee{
	private String name;
	private int age;
	private String address;
	public Employee(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getAge() {
		return age;
	}
}

//create a functional interface, which is a factory interface
interface EmployeeFactory{
	public Employee getEmployee(String name, int age, String address);
}


public class ConstructorReference {
	public static void main(String[] args) {
		EmployeeFactory employeeObj = Employee::new;
		Employee e = employeeObj.getEmployee("Asad Fx", 40, "4915-6, Walkley Avenue.");
		
		System.out.println("Name: "+e.getName()+" Age:  "+e.getAge()+"  Address:  "+e.getAddress());
	}
}
