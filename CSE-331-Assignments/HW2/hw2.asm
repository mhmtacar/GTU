.data
arr: .space 400
arraySize_msg: .asciiz "Enter a array size\n"
target_num_msg: .asciiz "Enter a target num\n"
array_data_enter: .asciiz "Enter the current data of array\n"
exit_msg: .asciiz "Exit from loop\n"
possible_str: .asciiz "Possible\n"
not_possible_str: .asciiz "Not Possible\n"


.text

main:

    #print arraySize_msg string
    li $v0, 4
    la $a0, arraySize_msg
    syscall

    #get the array size from user
    li $v0, 5
    syscall
    move $t0, $v0

    #print target_num_msg string
    li $v0, 4
    la $a0, target_num_msg
    syscall

    #get the target number from user
    li $v0, 5
    syscall
    move $t1, $v0

    # initialize s0 register with 0 in order to use in for loop
    addi $s0, $zero, 0


loop: 
      
      beq $s0, $t0, exit_loop   #compare s0 register and t0 register in order to exit loop
      #print array_data_enter string
      li $v0, 4                 
      la $a0, array_data_enter
      syscall
      #get the array data from user and assign t2 register
      li $v0, 5
      syscall
      move $t2, $v0
      # applying 2 bit shift to s0 register and assign t3 register in order to to indicate to which index of the array will be filled
      sll $t3, $s0, 2
      # filling the array with t2 that is taken by user.
      sw $t2, arr($t3)
      # incrementing s0 register with 1..
      addi $s0, $s0, 1
      j loop
      
      
exit_loop: 
           
           # filling the arguments which are sending to function
           move $a0, $t1
           la $a1, arr
           move $a2, $t0
           
           #calling CheckSumPossibility function
           jal CheckSumPossibility
           
           # assigning returning value to a0 register
           move $a0, $v0
           
           # If returning value from CheckSumPossibility function is 1, program enters this condition and prints "Possible".
           beq $a0, $zero, Else1
           li $v0, 4
           la $a0, possible_str
           syscall
           j exit
           
           # If returning value from CheckSumPossibility function is 0, program enters this condition and prints "Not Possible".
           Else1:
           li $v0, 4
           la $a0, not_possible_str
           syscall
           j exit
           
           # exit the program
           exit:
           li $v0, 10
           syscall
           

CheckSumPossibility:
                     
                     addi $sp, $sp, -16   # allocates 16 bytes for the stack
                     sw $ra, 12($sp)      # store $ra register to stack's specified place.
                     sw $a2, 8($sp)       # store $a2 register to stack's specified place. This is done to avoid losing function arguments.
                     sw $a1, 4($sp)       # store $a1 register to stack's specified place. This is done to avoid losing function arguments.
                     sw $a0, 0($sp)       # store $a0 register to stack's specified place. This is done to avoid losing function arguments.
                     
                 If:   
                 
                     bne $a0, $zero, ElseIf1 # This line checks the program will enter the If condition or not.
                     addi $v0, $zero, 1      # This line provides that this condition can return value.
                     addi $sp, $sp, 16       # This line shrinks the stack by 16 bytes so we find it as it is.
                     jr $ra                  # This line provides that the function go to where it was called.
                   
                 ElseIf1:
                   
                     slti $t4, $a2, 1        # This line checks the $a2 argument is less than 1 or not.
                     bne $t4, 1, ElseIf2     # This line checks the program will enter the ElseIf1 condition or not.
                     add $v0, $zero, $zero   # This line provides that this condition can return value.
                     addi $sp, $sp, 16       # This line shrinks the stack by 16 bytes so we find it as it is.
                     jr $ra                  # This line provides that the function go to where it was called.
                     
                 ElseIf2:
                 
                     slt $t5, $a0, $zero     # This line checks the $a0 argument is less than 0 or not.
                     bne $t5, 1, Else2       # This line checks the program will enter the ElseIf2 condition or not.
                     add $v0, $zero, $zero   # This line provides that this condition can return value.
                     addi $sp, $sp, 16       # This line shrinks the stack by 16 bytes so we find it as it is.
                     jr $ra                  # This line provides that the function go to where it was called.
                     
                     
                 Else2: 
                 
                     addi $a2, $a2, -1          # This line decrements the $a2 register value with 1.
                     jal CheckSumPossibility    # This lines calls the CheckSumPossibility function again.
                     lw $a0, 0($sp)             # In order take the current num parameter in function, I applied this line.
                     lw $a1, 4($sp)             # In order to take arr[] parameter, I applied this line.
                     lw $a2, 8($sp)             # In order to take the current size parameter in function, I applied this line.
                     lw $ra, 12($sp)            # In order to take address of called place, I applied this line.
                     move $s6, $v0              # This line assign the returning value of function to $s6 register.
                     
                     addi $s2, $a2, -1          # This line decrements $a2 register with 1 and assign $s2 register.
                     sll $s3, $s2, 2            # In order to access array's proper place, I applied shift left logical to $s2 register.
                     lw $s4, arr($s3)           # This line loads proper place of array to $s4 register from memory.
                     sub $a0, $a0, $s4          # This line decrements the $a0 register value with $s4 register.
                     jal CheckSumPossibility    # This line calls the CheckSumPossibility function again.
                     lw $a0, 0($sp)             # In order take the current num parameter in function, I applied this line.
                     lw $a1, 4($sp)             # In order to take arr[] parameter, I applied this line.
                     lw $a2, 8($sp)             # In order to take the current size parameter in function, I applied this line.
                     lw $ra, 12($sp)            # In order to take address of called place, I applied this line.
                     move $s7, $v0              # This line assign the returning value of function to $s7 register.
                     addi $sp, $sp, 16          # This line shrinks the size of the stack by 16 bytes.
                     or $v0, $s6, $s7           # In order to return 1 or 0 from function, I applied or operation to $s6 and $s7 register and
                                                # assign this value to $v0 register which is keeps returning value.
                                                
                     jr $ra     
                   
                                                                       
