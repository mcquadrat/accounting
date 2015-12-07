
			DIR="src/data/testdata"
			FILES=`ls $DIR/test_err_*.in`

			COUNTER=0
			for I in $FILES
			do
				java -jar ./dist/accounting-app.jar --input-file $I -l $DIR/test1.log 0.05 > $DIR/test_err_$COUNTER.out
				let COUNTER=COUNTER+1
			done

			exit 0
		