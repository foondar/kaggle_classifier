import java.text.Normalizer.Form;



Date d = new Date()
Date d2 = new Date()
d2.month += 50

while (d < d2) {
	d.month++
	println d
}

println "*** " + d2