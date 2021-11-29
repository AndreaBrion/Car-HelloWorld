package Unive.vehicles.fuel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents a type of fuel
 * @author Andrea Brion 860595
 * @since 1.0
 */
@XmlRootElement
@XmlType
public class FuelType implements Comparable<FuelType>{
    @XmlElement
    String type;
    @XmlAnyAttribute
    private final double ltperKmh;
    @XmlAnyAttribute
    private double costPerLiter;
    double FUEL_CONS;
    public FuelType(String t, double c, double l) {
        this.type = t;
        this.costPerLiter = c;
        this.ltperKmh = l;
    }
    FuelType(String t, double c) {
        this.ltperKmh = 0.0;
        type = t;
        costPerLiter = c;
    }
    public boolean isCompatible(FuelType tank) {
        //qui c'è un problema concettuale, in refule abbiamo la macchina che prende fuleType della tanica e passa se stessa al fuelType
        //fuelType ha bisogno di vedere un dettagli implementativo interno della Macchina
        //Se passassi un parametro Car ciò diventa ambiguo : è la macchina che chiede se la tanica è compatibile oppure è la macchina
        //chiede se il tipo di carburante con cui fa rifornimento è compatibile con quello della tanica?
        return tank.getType().equals(this.type);
    }
    public String getType() {
        return this.type;
    }
    public double getLtperKmh() {
        return this.ltperKmh;
    }
    public double getCostPerLiter() {
        return this.costPerLiter;
    }
    //normalmente i set non hanno tipo di ritorno, ma qui troviamo utile ritornare un boolenao
    //in base alla positività o negatività di costPerLiter (passato in input)
    public boolean setCostPerLiter(double costPerLiter) {
        if (costPerLiter < 0){
            this.costPerLiter = 0;
        return false;
        }
        else {
            double difference = Math.abs(this.costPerLiter - costPerLiter);
            double differenxePercentage = difference / this.costPerLiter;
            if (differenxePercentage < 0.2) {
                this.costPerLiter = costPerLiter;
                return true;
            }
            else
                return false;
        }
    }
    @Override //dato compareTo su FuelTank
    public int compareTo(FuelType o) {
        if (this.equals(o))
            return 0;
        else if (!this.type.equals(o.type))
                return this.type.compareTo(o.type);
        else if(this.ltperKmh != o.ltperKmh)
            return (int) (this.ltperKmh - o.ltperKmh);
        else
            return (int) (this.costPerLiter - o.costPerLiter);
    }// l'unico problema è che nella specifica del compareTo è indicato che
    //SE ritorna 0 allora gli oggetti sono uguali per forza
    //Se 2 oggetti sono uguali il compareTo ritorna 0
    //indicato con (x.compareTo(y)==0) == (x.equals(y))
    //Vedi appunti
    /*
    public static void main (String[] args) throws JAXBException, IOException {
        FuelType f = new FuelType("Diesel", 0.015, 0.01);
        marshal(f);
    }
    * Questo metodo va in crash in quanto non abbiamo un costruttore senza arogmento*/
    public FuelType() {
        this("",0.0);
    }
    public static void main (String[] args) throws JAXBException, IOException {
        FuelType f = new FuelType("Diesel", 0.015, 0.01);
        marshal(f);
        //FuelType f2 = unmarshall(); Così facciamo l'inverso, unmarshall() prende in automatico il file FuelType
       // System.out.println("ko");
    }
    static FuelType marshal(FuelType f) throws JAXBException, IOException {
            JAXBContext context = JAXBContext.newInstance(FuelType.class);
            return (FuelType) context.createUnmarshaller().unmarshal(new FileReader("./f.xml")); //scriviamo nel file xml l'informazione
        }
}







