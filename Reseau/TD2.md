## TD2 Réseau -> cisco
#### ex 1

<img width="724" height="195" alt="tableau ip addresses" src="https://github.com/user-attachments/assets/f176e193-94de-4d08-8924-433fd66f4f7c" />

```
! Router0 
en
conf t
hostname
router0JM

! Switch0
en
conf t
hostname switch0JM

! Switch1
en
conf t
hostname switch1JM

! Router1
en
conf t
hostname router1JM

! Switch 0
en
conf t
vlan 2
name administratif
exit

vlan 3
name production
exit

vlan 5
 name servers
exit

vlan 9
name dmz
exit

int range Fa0/1-24
shutdown
ex

int Fa0/24
switchport mode trunk 
switchport trunk allowed vlan 2,3,5,9
no shutdown
ex

int range Fa0/1-2
description Acces port Vlan administration
switchport mode access
switchport access vlan 2
no shutdown

int range Fa0/5-6
description Acces port Vlan production
switchport mode access
switchport access vlan 3
no shutdown

int range Fa0/10-11
description Acces port Vlan server
switchport mode access
switchport access vlan 5
no shutdown


int Fa0/20
description Acces port Vlan dmz
switchport mode access
switchport access vlan 9
no shutdown

! Routeur 0

int gig0/0
no shutdown
ex

interface gig0/0.2
encapsulation dot1q 2
ip address 192.168.10.1 255.255.255.0
ip helper-address 192.168.11.130
no shutdown
ex

interface gig0/0.3
encapsulation dot1q 3
ip address 192.168.11.1 255.255.255.128
ip helper-address 192.168.11.130
no shutdown
ex

interface gig0/0.5
encapsulation dot1q 5
ip address 192.168.11.129 255.255.255.224
no shutdown
ex

interface gig0/0.9
encapsulation dot1q 9
ip address 192.168.99.1 255.255.255.240
no shutdown
ex

interface gig0/1
no shutdown
ex 

interface gig0/1
ip address 65.18.45.7 255.0.0.0
ex

ip route 0.0.0.0 0.0.0.0 65.18.45.8


! Routeur 1
en 
conf t

int gig0/1 
ip address 65.18.45.8 255.0.0.0
no shutdown 
ex

int gig0/0
ip address 4.0.0.1 255.0.0.0
no shutdown
ex

ip route 0.0.0.0 0.0.0.0 65.18.45.7
```

NE PAS OUBLIER DE CONFIGURER ADRESSES DE GATEWAY
