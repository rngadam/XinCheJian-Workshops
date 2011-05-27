# Public and Private Keys

A public key is designed to be shared with the world, it can be considered a door lock without a key - everyone can see it but only the correct key can open the lock, the private key is the key itself, if you have this you can open the door, each private and public key are unique, you can create many different public keys from your private key, but you cannot create a private key from any public keys.

rule of thumb: 
 Share your public key with the world, print it on everything, stamp it everywhere, tattoo your dog with it.
 Never share your private key, guard it with your internet life, give it a passphrase to keep it locked up.

Windows users use Puttygen GUI tool save both your private and public key (RSA 2048), make sure you set a passphrase on the private key.

Linux/Mac/Unix users

    openssl genrsa -des3 -out ~/private.pem 2048 
    openssl rsa -in ~/private.pem -pubout -out ~/public.key

you should have two files in your home directory ~/ 

copy the contents of your public key

    --- sample key ---
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApj42p5QPgEYHbV8kT+ds
    pNw1arRKEXdfaMOHGGwpV/J6h+rFySHql7EEbKef+kL28LRx6Eh+YUIKWZBWQZzw
    1yDvPGK/RSKa4Fzp/8q8K1gr68pXMqKx4QphGYc2/k6oEOfqm9XjbJSsSvAZnTzd
    FuW1DSFChiRtp/17aZdU+DOA5e0cUqMqzKC1M6eMsg3Q/Qau9CbbHu8cAohkS+1Y
    UKYPZfAwFrgl0BIeKzB6PhNsIT6kx+TXcH3bqM9S6yz5onOLLewJRJIcKCyEejZ+
    Xes3azAsFOBAUWfBU1ptlDkrkhFnCDEPXQqib2L6Y+LITgaqgNLg+UwW7c05UH4p
    pQIDAQAB
    --- end sample key ---
    
Now setup your account in github.com > Account Settings > SSH Public Keys > add a Public Key: give it a title and paste the contents into the key field.

Move the private key into the right location for Linux/OSX/Unix

    mkdir ~/.ssh
    cp ~/public.key ~/.ssh/id_rsa
    chmod -R 600 ~/.ssh
    chmod 400 ~/.ssh/id_rsa

Sorry for windows users.. find out and update this wiki
